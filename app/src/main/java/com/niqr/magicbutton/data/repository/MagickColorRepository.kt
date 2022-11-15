package com.niqr.magicbutton.data.repository

import com.niqr.magicbutton.data.model.MagickColor
import kotlinx.coroutines.flow.Flow

interface MagickColorRepository: ColorGenerator {
    suspend fun generateColor()
    suspend fun magickColor(id: Long): List<MagickColor>
    fun lastMagickColor(): Flow<MagickColor?>
    suspend fun lastId(): Long
    suspend fun updateMagickColor(magickColor: MagickColor)
}