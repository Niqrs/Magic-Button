package com.niqr.magicbutton.ui.model

import androidx.compose.ui.graphics.Color
import com.niqr.magicbutton.data.model.MagickColor

data class MagickColorUiState(
    val id: Int,
    val color: Color,
    val isFavorite: Boolean
)

fun List<MagickColor>.toUiState():  List<MagickColorUiState> =
    this.mapIndexed() { index, magickColor ->
        MagickColorUiState(
            index,
            color = magickColor.color,
            isFavorite = magickColor.isFavorite
        )
    }
