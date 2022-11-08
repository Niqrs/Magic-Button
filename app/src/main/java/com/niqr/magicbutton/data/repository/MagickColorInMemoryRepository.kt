package com.niqr.magicbutton.data.repository

import com.niqr.magicbutton.data.datastore.StoreColorGenerationPreferences
import com.niqr.magicbutton.data.model.MagickColor
import com.niqr.magicbutton.data.model.MagickColorGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.take
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

    private val colorsListFlow = MutableSharedFlow<List<MagickColor>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private fun emitColorsFlow() {
        coroutineScope.launch { colorsListFlow.emit(colorsList) }
    }

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
        colorsList.add(
            MagickColor(
                id = (colorsList.lastOrNull()?.id ?: -1) +1,
                color = colorGenerator.value.generateColor(),
                isFavorite = MutableStateFlow(false)
            )
        )
        emitColorsFlow() //TODO: Do i need it anymore?
    }

    override suspend fun magickColors(pageNumber: Int, pageSize: Int): List<MagickColor> {
        //TODO: pageNumber is just an id of color, so i should rename it and remove pageSize
        return listOf(colorsList[pageNumber])
    }

    override fun lastId(): Int = colorsList.lastIndex

    override fun updateFavoriteStatus(magickColorId: Int) {
        colorsList[magickColorId].isFavorite.value = !colorsList[magickColorId].isFavorite.value
    }
}