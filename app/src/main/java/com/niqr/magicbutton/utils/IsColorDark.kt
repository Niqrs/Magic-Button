package com.niqr.magicbutton.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.ColorUtils

fun Color.isDark() = ColorUtils.calculateLuminance(this.toArgb()) < 0.5f