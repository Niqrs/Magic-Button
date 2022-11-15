package com.niqr.magicbutton.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.niqr.magicbutton.R


@Composable
fun MagickColorActions(
    modifier: Modifier = Modifier,
    clickable: Boolean,
    isFavorite: Boolean,
    onCopyClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val favoriteIcon = if (isFavorite)
        Icons.Default.Favorite
    else
        Icons.Default.FavoriteBorder

    Row(modifier = modifier) {
        IconButton(
            onClick = onCopyClick,
            enabled = clickable
        ) {
            Icon(
                painter = painterResource(id = R.drawable.round_content_copy_24),
                contentDescription = "Edit"
            )
        }
        IconButton(
            onClick = onFavoriteClick,
            enabled = clickable
        ) {
            Icon(
                imageVector = favoriteIcon,
                contentDescription = "Save"
            )
        }
    }
}