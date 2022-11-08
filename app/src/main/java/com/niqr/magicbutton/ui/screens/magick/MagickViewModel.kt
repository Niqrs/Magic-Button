package com.niqr.magicbutton.ui.screens.magick

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.niqr.magicbutton.data.model.MagickColorGenerator
import com.niqr.magicbutton.data.repository.MagickColorPaginationSource
import com.niqr.magicbutton.data.repository.MagickColorRepository
import com.niqr.magicbutton.ui.model.toUiState
import com.niqr.magicbutton.ui.screens.magick.model.MagickScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MagickViewModel @Inject constructor(
    private val magickColorRepository: MagickColorRepository
) : ViewModel() {
    private val magickColorsPager = Pager(PagingConfig(pageSize = 1)) { //TODO: optimize it
        MagickColorPaginationSource(magickColorRepository)
    }.flow.cachedIn(viewModelScope)

    private val _uiState = MutableStateFlow(MagickScreenUiState(magickColorsPager.toUiState()))
    val uiState = _uiState.asStateFlow()

    init {
        //?
    }

    fun updateColorGenerator(
        magickColorGenerator: MagickColorGenerator
    ) = magickColorRepository.updateColorGenerator(magickColorGenerator)

    fun createNewColor() {
        magickColorRepository.generateColor()
    }

    fun onFavoriteClick(id: Int = magickColorRepository.lastId()) {
        magickColorRepository.updateFavoriteStatus(id)
    }
}