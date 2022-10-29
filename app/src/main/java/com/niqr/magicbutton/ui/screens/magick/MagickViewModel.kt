package com.niqr.magicbutton.ui.screens.magick

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.niqr.magicbutton.data.MagickColorRepository
import com.niqr.magicbutton.ui.model.toUiState
import com.niqr.magicbutton.ui.screens.magick.model.MagickScreenUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MagickViewModel @Inject constructor(
    private val magickColorRepository: MagickColorRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(MagickScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            magickColorRepository.magickColors().onEach { colors ->
                val colorItems = colors.map { it.toUiState() }
                _uiState.update {
                    it.copy(magickColors = colorItems)
                }
            }.launchIn(viewModelScope)
        }
    }

    fun createNewColor() {
        Log.d("MAGICK", "createNewColor()")
        magickColorRepository.generateColor()
    }

    fun onFavoriteClick() {
        TODO("Not yet implemented")
    }
}