package com.niqr.magicbutton.ui.screens.magick

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.niqr.magicbutton.ui.model.MagickColorUiState
import com.niqr.magicbutton.ui.screens.magick.components.AllMagickColors
import com.niqr.magicbutton.ui.screens.magick.components.FavoriteMagickColors

@ExperimentalMaterial3Api
@Composable
fun MagickColorsDrawer(
    drawerState: DrawerState,
    allMagickColors: LazyPagingItems<MagickColorUiState>,
    favoriteMagickColors: LazyPagingItems<MagickColorUiState>,
    latestMagickColor: MagickColorUiState?,
    colorsListState: LazyListState,
    onCopyClick: (MagickColorUiState) -> Unit,
    onFavoriteClick: (MagickColorUiState) -> Unit,
    content: @Composable () -> Unit
) {
    var onlyFavorite by remember {
        mutableStateOf(false)
    }


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        NavigationBar(modifier = Modifier.weight(1f)) {
                            NavigationBarItem(
                                icon = { Icon(Icons.Default.Star, contentDescription = "History") },
                                label = { Text("History") },
                                selected = !onlyFavorite,
                                onClick = { onlyFavorite = false }
                            )
                            NavigationBarItem(
                                icon = { Icon(Icons.Filled.Favorite, contentDescription = "Favorite") },
                                label = { Text("Favorite") },
                                selected = onlyFavorite,
                                onClick = { onlyFavorite = true }
                            )
                        }
                    }
                ) { paddingValues ->
                    if (!onlyFavorite) {
                        AllMagickColors(
                            modifier = Modifier.padding(paddingValues),
                            magickColors = allMagickColors,
                            onCopyClick = onCopyClick,
                            onFavoriteClick = onFavoriteClick,
                            colorsListState = colorsListState
                        )
                    } else {
                        FavoriteMagickColors(
                            modifier = Modifier.padding(paddingValues),
                            magickColors = favoriteMagickColors,
                            latestMagickColor = latestMagickColor,
                            onCopyClick = onCopyClick,
                            onFavoriteClick = onFavoriteClick,
                            colorsListState = colorsListState
                        )
                    }
                }
            }
        },
        content = content
    )
}