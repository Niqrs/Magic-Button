package com.niqr.magicbutton.data

import com.niqr.magicbutton.data.model.MagickColor
import com.niqr.magicbutton.utils.generateRgbColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.util.LinkedList

class MagickColorInMemoryRepository : MagickColorRepository {

    private val colorsList = LinkedList( // Default colors
        listOf(
            MagickColor(generateRgbColor(), false),
            MagickColor(generateRgbColor(), false),
            MagickColor(generateRgbColor(), false),
        )
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
        emitColorsFlow()
    }

    override fun generateColor() {
        colorsList.add(MagickColor(generateRgbColor(), false))
        emitColorsFlow()
    }

    override fun magickColors(): Flow<List<MagickColor>> = colorsListFlow

    override fun updateFavoriteStatus(magickColorId: Int) {
        colorsList[magickColorId] =
            colorsList[magickColorId].copy(
                isFavorite = !colorsList[magickColorId].isFavorite)

        emitColorsFlow()
    }
}