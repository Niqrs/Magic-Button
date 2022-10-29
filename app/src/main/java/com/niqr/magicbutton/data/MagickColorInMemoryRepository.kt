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

    init {
        CoroutineScope(Dispatchers.IO).launch {
            colorsListFlow.emit(colorsList)
        }
    }

    override fun generateColor() {
        colorsList.add(MagickColor(generateRgbColor(), false))
        CoroutineScope(Dispatchers.IO).launch {
            colorsListFlow.emit(colorsList)
        }
    }

    override fun magickColors(): Flow<List<MagickColor>> = colorsListFlow

    override fun updateFavoriteStatus(magickColorId: Int) {
        TODO("Not yet implemented")
    }
}