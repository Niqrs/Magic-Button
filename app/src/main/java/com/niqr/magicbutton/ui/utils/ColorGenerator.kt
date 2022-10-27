package com.niqr.magicbutton.ui.utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

fun generateColor(): Color {
    val rnd = Random
    return Color(rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
}