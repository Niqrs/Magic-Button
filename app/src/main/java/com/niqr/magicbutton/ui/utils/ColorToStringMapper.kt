package com.niqr.magicbutton.ui.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun Color.toRgbString(): String = Integer.toHexString(this.toArgb())