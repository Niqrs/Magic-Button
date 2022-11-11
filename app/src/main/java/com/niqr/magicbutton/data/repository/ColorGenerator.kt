package com.niqr.magicbutton.data.repository

import com.niqr.magicbutton.data.model.MagickColorGenerator
import kotlinx.coroutines.flow.StateFlow

interface ColorGenerator {
    val colorGenerator: StateFlow<MagickColorGenerator>
    fun updateColorGenerator(magickColorGenerator: MagickColorGenerator)
}