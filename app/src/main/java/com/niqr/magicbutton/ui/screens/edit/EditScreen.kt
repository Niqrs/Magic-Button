package com.niqr.magicbutton.ui.screens.edit

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.niqr.magicbutton.ui.theme.MagicButtonTheme

@ExperimentalMaterial3Api
@Composable
fun EditScreen() {
    Scaffold(
        bottomBar = {
            BottomAppBar() {
                LazyRow(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(10) {
                        Text(text = "TODO ")
                    }
                }
            }
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun EditScreenPreview() {
    MagicButtonTheme {
        EditScreen()
    }
}