package com.niqr.magicbutton.data.model

import kotlinx.coroutines.flow.MutableStateFlow
import androidx.compose.ui.graphics.Color

data class MagickColor(
    val id: Int = 0,
    val color: Color,
    val isFavorite: MutableStateFlow<Boolean>
)