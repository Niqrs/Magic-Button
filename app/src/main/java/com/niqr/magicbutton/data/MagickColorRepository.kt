package com.niqr.magicbutton.data

import com.niqr.magicbutton.data.model.MagickColor
import kotlinx.coroutines.flow.Flow

interface MagickColorRepository {

    fun generateColor()
    fun magickColors(): Flow<List<MagickColor>>
    fun updateFavoriteStatus(magickColorId: Int)
}