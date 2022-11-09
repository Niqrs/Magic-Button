package com.niqr.magicbutton.data.repository

import com.niqr.magicbutton.data.model.MagickColor
import com.niqr.magicbutton.data.model.MagickColorGenerator
import kotlinx.coroutines.flow.Flow

interface MagickColorRepository {
    fun updateColorGenerator(magickColorGenerator: MagickColorGenerator)
    fun generateColor()
    suspend fun magickColor(id: Int): List<MagickColor>
    fun lastMagickColor(): Flow<MagickColor?>
    fun lastId(): Int
    fun updateFavoriteStatus(magickColorId: Int)
}