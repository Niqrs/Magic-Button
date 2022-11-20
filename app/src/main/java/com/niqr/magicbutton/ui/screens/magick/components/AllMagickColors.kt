package com.niqr.magicbutton.ui.screens.magick.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.niqr.magicbutton.ui.components.MagickColorItem
import com.niqr.magicbutton.ui.model.MagickColorUiState

@Composable
fun AllMagickColors(
    modifier: Modifier = Modifier,
    magickColors: LazyPagingItems<MagickColorUiState>,
    onCopyClick: (MagickColorUiState) -> Unit,
    onFavoriteClick: (MagickColorUiState) -> Unit,
    colorsListState: LazyListState
) {
    LazyColumn(
        state = colorsListState,
        modifier = modifier
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(magickColors) { magickColor ->
            if (magickColor != null) {
                MagickColorItem(
                    magickColor = magickColor,
                    onCopyClick = onCopyClick,
                    onFavoriteClick = onFavoriteClick
                )
            }
            Divider( //TODO: Should it be inside if?
                modifier = Modifier
                    .height(1.dp)
                    .padding(end = 148.dp),
                color = DividerDefaults.color.copy(alpha = 0.7f)
            )
        }
    }
}