package com.niqr.magicbutton.data.repository

import androidx.paging.PagingSource
import com.niqr.magicbutton.data.datastore.StoreColorGenerationPreferences
import com.niqr.magicbutton.data.model.ColorWrapper
import com.niqr.magicbutton.data.model.MagickColor
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
    storeColorGenerationPreferences: StoreColorGenerationPreferences
) : MagickColorRepository, ColorGeneratorImpl(storeColorGenerationPreferences) {

    private val colorsList = LinkedList(
        listOf<MagickColor>()
    )
    private val lastColor = MutableStateFlow(colorsList.lastOrNull())

    init {
        CoroutineScope(Dispatchers.IO).launch { // Default colors
            colorGenerator.drop(1).take(1).onEach {
                repeat(15) {
                    generateColor()
                }
            }.collect()
        }
    }

    override suspend fun generateColor() {
        val magickColor = MagickColor(
            id = (colorsList.lastOrNull()?.id ?: 0) +1,
            color = ColorWrapper(colorGenerator.value.generateColor()),
            isFavorite = MutableStateFlow(false)
        )
        colorsList.add(magickColor)
        lastColor.update { colorsList.lastOrNull() }
    }

    override suspend fun magickColor(id: Int): List<MagickColor> {
        val color: MagickColor = colorsList[id]
//        lastColor.value.run { There should be fix in future
//            if (this != null && this.id <= id)
//                lastColor.update {color}
//        }
        if ((lastColor.value?.id ?: -1) <= id)
            lastColor.update {color}
        return listOf(color)
    }

    override fun lastMagickColor(): Flow<MagickColor?> {
        return lastColor
    }

    override fun favoriteColors(): PagingSource<Int, MagickColor> {
        TODO("Not yet implemented")
    }

    override suspend fun lastId(): Int {
        return lastColor.value?.id ?: -1
    }

    override suspend fun updateMagickColor(magickColor: MagickColor) {
        colorsList[magickColor.id] = magickColor
    }
}