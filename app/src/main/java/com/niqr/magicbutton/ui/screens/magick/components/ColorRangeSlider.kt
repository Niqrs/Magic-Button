package com.niqr.magicbutton.ui.screens.magick.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorRangeSlider(
    header: String,
    value: ClosedFloatingPointRange<Float>,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit
) {
    Column {
        Text(text = header)
        RangeSlider(
            value = value,
            onValueChange = onValueChange,
            valueRange = 0f..255f
        )
    }
}