package com.niqr.magicbutton.ui.screens.magick

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.niqr.magicbutton.ui.screens.magick.components.MagickFloatingActionButton
import com.niqr.magicbutton.ui.screens.magick.components.MagickTopAppBar
import com.niqr.magicbutton.ui.theme.MagicButtonTheme
import com.niqr.magicbutton.utils.toRgbString
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun MagickScreen(
    viewModel: MagickViewModel
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val floatingActionButtonAlpha = 1f - scrollBehavior.state.collapsedFraction
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val colorsListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    val uiState by viewModel.uiState.collectAsState()
    val magickColors = uiState.magickColors.collectAsLazyPagingItems()
    val latestMagickColorFlow = uiState.latestMagickColor
    val latestMagickColor by latestMagickColorFlow.collectAsState(null)
    
    val clipboardManager = LocalClipboardManager.current

    var openDialog by remember { mutableStateOf(false) }
    if (openDialog)
        SettingsDialog(
            closeDialog = { openDialog = false },
            onSaveSettings = viewModel::updateColorGenerator
        )

    MagickColorsDrawer(
        drawerState = drawerState,
        magickColors = magickColors,
        colorsListState = colorsListState,
        onCopyClick = {
            clipboardManager.setText(AnnotatedString(it.color.toRgbString()))
        },
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
                    magickColor = latestMagickColor,
                    onFavoriteClick = viewModel::onFavoriteClick,
                    onNavigationClick = { coroutineScope.launch { drawerState.open() }},
                    onCopyClick = {
                        clipboardManager.setText(AnnotatedString(it.color.toRgbString()))
                    },
                    onSettingsClick = { openDialog = true }
                )
            },
            floatingActionButton = {
                MagickFloatingActionButton(
                    modifier = Modifier.alpha(floatingActionButtonAlpha),
                    onClick = {
                        coroutineScope.launch {
                            colorsListState.scrollToItem(0)
                        }
                        viewModel.createNewColor()
                        magickColors.retry()
                    }
                )
            }
        ) { contentPadding ->
            Surface(modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
                color = latestMagickColor?.color
                    ?: MaterialTheme.colorScheme.surface
            ) {
                LazyColumn {
                    // It is only for nestedScroll
                }
            }
        }
    }

    LaunchedEffect(key1 = null) {
        latestMagickColorFlow.onEach {
            magickColors.retry()
        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
private fun MagickScreenPreview() {
    MagicButtonTheme {
        MagickScreen(
            viewModel = hiltViewModel()
        )
    }
}