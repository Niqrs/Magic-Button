package com.niqr.magicbutton.ui.model

import androidx.compose.ui.graphics.Color
import androidx.paging.PagingData
import androidx.paging.map
import com.niqr.magicbutton.data.model.MagickColor
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

data class MagickColorUiState(
    val id: Int,
    val color: Color,
    val isFavorite: StateFlow<Boolean>
)


fun Flow<PagingData<MagickColor>>.toUiState():  Flow<PagingData<MagickColorUiState>> =
    this.map() { pagingData ->
        pagingData.map { magickColor ->
            magickColor.toUiState()
        }
    }

@JvmName("toUiStateMagickColorFlow")
fun Flow<MagickColor?>.toUiState(): Flow<MagickColorUiState?> =
    this.map { magickColor ->
        magickColor?.toUiState()
    }

fun MagickColor.toUiState(): MagickColorUiState =
    MagickColorUiState(
        id = this.id,
        color = this.color,
        isFavorite = this.isFavorite
    )