package com.niqr.magicbutton.data.repository

import com.niqr.magicbutton.data.model.MagickColor
import com.niqr.magicbutton.data.model.MagickColorGenerator

interface MagickColorRepository {
    fun updateColorGenerator(magickColorGenerator: MagickColorGenerator)
    fun generateColor()
    suspend fun magickColors(pageNumber: Int, pageSize: Int): List<MagickColor> //TODO: Should i use id as argument, because i use page size as 1?
    fun lastId(): Int
    fun updateFavoriteStatus(magickColorId: Int)
}