package com.niqr.magicbutton.ui.screens.magick.model

import androidx.paging.PagingData
import com.niqr.magicbutton.ui.model.MagickColorUiState
import kotlinx.coroutines.flow.Flow


data class MagickScreenUiState(
    val allMagickColors: Flow<PagingData<MagickColorUiState>>,
    val favoriteMagickColors: Flow<PagingData<MagickColorUiState>>,
    val latestMagickColor: Flow<MagickColorUiState?>
)