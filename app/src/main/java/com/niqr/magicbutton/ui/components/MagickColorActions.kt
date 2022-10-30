package com.niqr.magicbutton.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun MagickColorActions(
    modifier: Modifier = Modifier,
    clickable: Boolean,
    isFavorite: Boolean,
    onEditClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val favoriteIcon = if (isFavorite)
        Icons.Default.Favorite
    else
        Icons.Default.FavoriteBorder

    Row(modifier = modifier) {
        IconButton(
            onClick = onEditClick,
            enabled = clickable
        ) {
            Icon(
                Icons.Default.Edit,
                contentDescription = "Edit"
            )
        }
        IconButton(
            onClick = onFavoriteClick,
            enabled = clickable
        ) {
            Icon(
                favoriteIcon,
                contentDescription = "Save"
            )
        }
    }
}