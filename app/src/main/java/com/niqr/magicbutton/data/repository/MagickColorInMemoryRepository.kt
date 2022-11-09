package com.niqr.magicbutton.data.repository

import com.niqr.magicbutton.data.datastore.StoreColorGenerationPreferences
import com.niqr.magicbutton.data.model.MagickColor
import com.niqr.magicbutton.data.model.MagickColorGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.LinkedList
import javax.inject.Inject

class MagickColorInMemoryRepository @Inject constructor(
    private val storeColorGenerationPreferences: StoreColorGenerationPreferences
) : MagickColorRepository {
    private val colorGenerator = MutableStateFlow(StoreColorGenerationPreferences.defaultGenerator())

    private val colorsList = LinkedList(
        listOf<MagickColor>()
    )
    private var lastColor = MutableStateFlow(colorsList.lastOrNull())

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        CoroutineScope(Dispatchers.IO).launch { // Update color generator on each preferences change
            storeColorGenerationPreferences.getPreferences.onEach { newGenerator ->
                colorGenerator.value = newGenerator
            }.collect()
        }

        coroutineScope.launch{ // Default colors
            colorGenerator.drop(1).take(1).onEach {
                repeat(15) {
                    generateColor()
                }
            }.collect()
        }
    }

    override fun updateColorGenerator(magickColorGenerator: MagickColorGenerator) {
        coroutineScope.launch {
            storeColorGenerationPreferences.savePreferences(magickColorGenerator)
        }
    }

    override fun generateColor() {
        val magickColor = MagickColor(
            id = (colorsList.lastOrNull()?.id ?: -1) +1,
            color = colorGenerator.value.generateColor(),
            isFavorite = MutableStateFlow(false)
        )
        colorsList.add(magickColor)
        lastColor.update { colorsList.lastOrNull() }
    }

    override suspend fun magickColor(id: Int): List<MagickColor> {
        return listOf(colorsList[id])
    }

    override fun lastMagickColor(): Flow<MagickColor?> {
        return lastColor
    }

    override fun lastId(): Int = colorsList.lastIndex

    override fun updateFavoriteStatus(magickColorId: Int) {
        colorsList[magickColorId].isFavorite.value = !colorsList[magickColorId].isFavorite.value
    }
}