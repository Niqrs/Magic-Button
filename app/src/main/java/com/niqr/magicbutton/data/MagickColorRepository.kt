package com.niqr.magicbutton.data

import com.niqr.magicbutton.data.model.MagickColor
import com.niqr.magicbutton.data.model.MagickColorGenerator
import kotlinx.coroutines.flow.Flow

interface MagickColorRepository {
    fun updateColorGenerator(magickColorGenerator: MagickColorGenerator)
    fun generateColor()
    fun magickColors(): Flow<List<MagickColor>>
    fun updateFavoriteStatus(magickColorId: Int)
}