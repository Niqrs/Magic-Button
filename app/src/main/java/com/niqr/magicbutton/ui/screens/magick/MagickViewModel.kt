package com.niqr.magicbutton.ui.screens.magick

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import com.niqr.magicbutton.data.model.MagickColorGenerator
import com.niqr.magicbutton.data.repository.MagickColorPaginationSource
import com.niqr.magicbutton.data.repository.MagickColorRepository
import com.niqr.magicbutton.ui.model.MagickColorUiState
import com.niqr.magicbutton.ui.model.toEntity
import com.niqr.magicbutton.ui.model.toUiState
import com.niqr.magicbutton.ui.screens.magick.model.MagickScreenUiState
import com.niqr.magicbutton.ui.screens.magick.utils.pagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MagickViewModel @Inject constructor(
    private val magickColorRepository: MagickColorRepository
) : ViewModel() {
    private val allMagickColorsFlow = Pager(pagingConfig) {
        MagickColorPaginationSource(magickColorRepository)
    }.flow
    private val favoriteMagickColorsFlow = Pager(pagingConfig) {
        magickColorRepository.favoriteColors()
    }.flow

    private val _uiState = MutableStateFlow(
        MagickScreenUiState(
            allMagickColorsFlow.toUiState(),
            favoriteMagickColorsFlow.toUiState(),
            magickColorRepository.lastMagickColor().toUiState().stateIn(
                viewModelScope,
                SharingStarted.Eagerly,
                null
            )
        )
    )
    val uiState = _uiState.asStateFlow()

    fun updateColorGenerator(
        magickColorGenerator: MagickColorGenerator
    ) = magickColorRepository.updateColorGenerator(magickColorGenerator)

    fun createNewColor() {
        viewModelScope.launch {
            magickColorRepository.generateColor()
        }
    }

    fun onFavoriteClick(magickColor: MagickColorUiState) {
        magickColor.isFavorite.value = !magickColor.isFavorite.value
        viewModelScope.launch {
            magickColorRepository.updateMagickColor(magickColor.toEntity())
        }
    }
}