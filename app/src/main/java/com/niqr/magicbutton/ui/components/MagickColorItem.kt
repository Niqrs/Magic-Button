package com.niqr.magicbutton.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niqr.magicbutton.ui.theme.MagicButtonTheme
import com.niqr.magicbutton.ui.utils.generateColor
import com.niqr.magicbutton.ui.utils.toRgbString

@Composable
fun MagickColorItem(
    color: Color,
    isFavorite: Boolean,
    onEditClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Surface(
        color = color
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "#${color.toRgbString()}")
            MagickColorActions(
                isFavorite = isFavorite,
                onEditClick = onEditClick,
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}

@Preview
@Composable
private fun MagickColorItemPreview() {
    var isFavorite by remember {
        mutableStateOf(false)
    }
    MagicButtonTheme {
        MagickColorItem(
            color = generateColor(),
            isFavorite = isFavorite,
            onEditClick = {},
            onFavoriteClick = { isFavorite = !isFavorite }
        )
    }
}