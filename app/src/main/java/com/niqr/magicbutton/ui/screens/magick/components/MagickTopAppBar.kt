package com.niqr.magicbutton.ui.screens.magick.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.niqr.magicbutton.ui.components.MagickColorActions
import com.niqr.magicbutton.ui.theme.MagicButtonTheme

@ExperimentalMaterial3Api
@Composable
fun MagickTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    scrollBehavior: TopAppBarScrollBehavior? = null,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onNavigationClick: () -> Unit,
    onEditClick: () -> Unit
) {
    TopAppBar(
        title = {Text(text = title)},
        modifier = modifier,
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Colors"
                )
            }
        },
        actions = {
            MagickColorActions(
                isFavorite = isFavorite,
                onEditClick = onEditClick,
                onFavoriteClick = onFavoriteClick
            )
        },
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun MagickTopAppBarPreview() {
    var isFavorite by remember {
        mutableStateOf(false)
    }
    MagicButtonTheme {
        MagickTopAppBar(
            isFavorite = isFavorite,
            onFavoriteClick = {isFavorite = !isFavorite},
            onNavigationClick = {},
            onEditClick = {}
        )
    }
}