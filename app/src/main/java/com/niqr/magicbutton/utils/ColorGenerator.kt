package com.niqr.magicbutton.utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun generateRgbColor(): Color {
    val rnd = Random
    return Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
}