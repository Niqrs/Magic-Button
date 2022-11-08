package com.niqr.magicbutton.ui.screens.magick

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.niqr.magicbutton.ui.screens.magick.components.MagickFloatingActionButton
import com.niqr.magicbutton.ui.screens.magick.components.MagickTopAppBar
import com.niqr.magicbutton.ui.theme.MagicButtonTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalLifecycleComposeApi::class)
@ExperimentalMaterial3Api
@Composable
fun MagickScreen(
    viewModel: MagickViewModel,
    onEditClick: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val floatingActionButtonAlpha = 1f - scrollBehavior.state.collapsedFraction
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val magickColors = uiState.magickColors.collectAsLazyPagingItems()

    var openDialog by remember { mutableStateOf(false) }
    if (openDialog)
        SettingsDialog(
            closeDialog = { openDialog = false },
            onSaveSettings = viewModel::updateColorGenerator
        )

    MagickColorsDrawer(
        drawerState = drawerState,
        magickColors = magickColors,
        onFavoriteClick = viewModel::onFavoriteClick
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MagickTopAppBar(
                    title = "Magick Screen",
                    scrollBehavior = scrollBehavior,
                    magickColor = magickColors.itemSnapshotList.firstOrNull(), //TODO: It doesn't look safety...
                    onFavoriteClick = viewModel::onFavoriteClick,
                    onNavigationClick = { coroutineScope.launch { drawerState.open() }},
                    onEditClick = onEditClick,
                    onSettingsClick = { openDialog = true }
                )
            },
            floatingActionButton = {
                MagickFloatingActionButton(
                    modifier = Modifier.alpha(floatingActionButtonAlpha),
                    onClick = {
                        viewModel.createNewColor()
                        //TODO: lazyList should be returned to start
                        magickColors.retry()
                    }
                )
            }
        ) { contentPadding ->
            Surface(modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
                color = magickColors.itemSnapshotList.firstOrNull()?.color //TODO: It doesn't look safety too...
                    ?: MaterialTheme.colorScheme.surface
            ) {
                LazyColumn {
                    // It is only for nestedScroll
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun MagickScreenPreview() {
    MagicButtonTheme {
        MagickScreen(
            viewModel = hiltViewModel(),
            onEditClick = {}
        )
    }
}