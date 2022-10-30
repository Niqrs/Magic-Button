package com.niqr.magicbutton.ui.screens.magick.model

import com.niqr.magicbutton.ui.model.MagickColorUiState


data class MagickScreenUiState(
    val magickColors: List<MagickColorUiState> = listOf(),
//    val category: MagickScreenCategory = MagickScreenCategory.AllColors TODO: Should i use it?
)

//enum class MagickScreenCategory {
//    AllColors, FavoriteColors
//}