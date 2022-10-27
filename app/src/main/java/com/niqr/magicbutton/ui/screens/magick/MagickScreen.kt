package com.niqr.magicbutton.ui.screens.magick

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.niqr.magicbutton.ui.screens.magick.components.MagickFloatingActionButton
import com.niqr.magicbutton.ui.screens.magick.components.MagickTopAppBar
import com.niqr.magicbutton.ui.theme.MagicButtonTheme
import com.niqr.magicbutton.ui.utils.generateColor
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun MagickScreen() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val colors by rememberSaveable() {
        mutableStateOf(List(25) { generateColor() })
    }

    MagickColorsDrawer(
        drawerState = drawerState,
        colors = colors
    ) {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                MagickTopAppBar(
                    title = "Magick Screen",
                    scrollBehavior = scrollBehavior,
                    onNavigationClick = { coroutineScope.launch { drawerState.open() }}
                )
            },
            floatingActionButton = {
                MagickFloatingActionButton(
                    onClick = { /*TODO*/ }
                )
            }
        ) { contentPadding ->
            Surface(modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize(),
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
        MagickScreen()
    }
}
