package com.niqr.magicbutton.data.repository

import com.niqr.magicbutton.data.model.MagickColor
import kotlinx.coroutines.flow.Flow

interface MagickColorRepository: ColorGenerator {
    fun generateColor()
    suspend fun magickColor(id: Int): List<MagickColor>
    fun lastMagickColor(): Flow<MagickColor?>
    fun lastId(): Int
    fun updateFavoriteStatus(magickColorId: Int)
}