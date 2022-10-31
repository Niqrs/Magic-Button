package com.niqr.magicbutton.ui.screens.magick.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
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
import com.niqr.magicbutton.data.datastore.StoreColorGenerationPreferences
import com.niqr.magicbutton.ui.components.MagickColorActions
import com.niqr.magicbutton.ui.model.MagickColorUiState
import com.niqr.magicbutton.ui.theme.MagicButtonTheme

@ExperimentalMaterial3Api
@Composable
fun MagickTopAppBar(
    modifier: Modifier = Modifier,
    title: String = "",
    scrollBehavior: TopAppBarScrollBehavior? = null,
    magickColor: MagickColorUiState?,
    onEditClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    onNavigationClick: () -> Unit,
    onSettingsClick: () -> Unit
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
            Row {
                MagickColorActions(
                    clickable = magickColor != null,
                    isFavorite = magickColor?.isFavorite ?: false,
                    onEditClick = onEditClick,
                    onFavoriteClick = onFavoriteClick
                )
                IconButton(onClick = onSettingsClick) {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Generation Settings"
                    )
                }
            }
        },
    )
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun MagickTopAppBarPreview() {
    val generator = StoreColorGenerationPreferences.defaultGenerator()
    var magickColor by remember {
        mutableStateOf(
            MagickColorUiState(0, generator.generateColor(), false)
        )
    }
    MagicButtonTheme {
        MagickTopAppBar(
            magickColor = magickColor,
            onEditClick = {},
            onFavoriteClick = {magickColor = magickColor.copy(isFavorite = !magickColor.isFavorite)},
            onNavigationClick = {},
            onSettingsClick = {}
        )
    }
}
