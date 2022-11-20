package com.niqr.magicbutton.data.repository

import androidx.paging.PagingSource
import com.niqr.magicbutton.data.model.MagickColor
import kotlinx.coroutines.flow.Flow

interface MagickColorRepository: ColorGenerator {
    suspend fun generateColor()
    suspend fun magickColor(id: Int): List<MagickColor>
    fun lastMagickColor(): Flow<MagickColor?>
    fun favoriteColors(): PagingSource<Int, MagickColor>
    suspend fun lastId(): Int
    suspend fun updateMagickColor(magickColor: MagickColor)
}