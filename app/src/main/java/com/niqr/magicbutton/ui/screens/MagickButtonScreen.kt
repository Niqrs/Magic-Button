package com.niqr.magicbutton.ui.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.niqr.magicbutton.navigation.NavigationTree
import com.niqr.magicbutton.ui.screens.magick.MagickScreen
import com.niqr.magicbutton.ui.screens.magick.MagickViewModel

@ExperimentalMaterial3Api
@Composable
fun MagickButtonScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavigationTree.Magick.name) {
        composable(NavigationTree.Magick.name) {
            val viewModel: MagickViewModel = hiltViewModel()
            MagickScreen(viewModel)
        }
    }
}