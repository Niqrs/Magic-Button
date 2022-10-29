package com.niqr.magicbutton.ui.screens.magick

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import com.niqr.magicbutton.ui.components.MagickColorItem
import com.niqr.magicbutton.ui.model.MagickColorUiState

@ExperimentalMaterial3Api
@Composable
fun MagickColorsDrawer(
    drawerState: DrawerState,
    magickColors: List<MagickColorUiState>,
    content: @Composable () -> Unit,
    //TODO: OnItemClickListener?
) {
    var onlyFavorite by remember {
        mutableStateOf(false)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
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
                    LazyColumn(
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        items(magickColors.reversed()) { magickColor ->
                            MagickColorItem(
                                color = magickColor.color,
                                isFavorite = magickColor.isFavorite,
                                onEditClick = { /*TODO*/ },
                                onFavoriteClick = { /*TODO*/ }
                            )
                        }
                    }
                }
            }
        },
        content = content
    )
}