package com.niqr.magicbutton.ui.screens.magick.model

import androidx.paging.PagingData
import com.niqr.magicbutton.ui.model.MagickColorUiState
import kotlinx.coroutines.flow.Flow


data class MagickScreenUiState(
    val magickColors: Flow<PagingData<MagickColorUiState>>,
//    val category: MagickScreenCategory = MagickScreenCategory.AllColors TODO: Should i use it?
)

//enum class MagickScreenCategory {
//    AllColors, FavoriteColors
//}