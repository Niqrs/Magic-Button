package com.niqr.magicbutton.ui.model

import androidx.compose.ui.graphics.Color
import com.niqr.magicbutton.data.model.MagickColor

data class MagickColorUiState(
    val color: Color,
    val isFavorite: Boolean
)

fun MagickColor.toUiState() = MagickColorUiState(
    color = this.color,
    isFavorite = this.isFavorite
)
