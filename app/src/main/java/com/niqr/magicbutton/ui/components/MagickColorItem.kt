package com.niqr.magicbutton.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import com.niqr.magicbutton.utils.generateRgbColor
import com.niqr.magicbutton.utils.toRgbString

@Composable
fun MagickColorItem(
    color: Color,
    isFavorite: Boolean,
    onEditClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .padding(
                top = 2.dp,
                bottom = 2.dp,
                end = 32.dp
            ),
        shape = RoundedCornerShape(
            bottomEndPercent = 100,
            topEndPercent = 100
        ),
        color = color
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(start = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "#${color.toRgbString()}")
            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.secondaryContainer)
                    .fillMaxHeight()
                    .padding(start = 6.dp, end = 12.dp),
            ) {
                MagickColorActions(
                    modifier = Modifier.align(Alignment.Center),
                    clickable = true,
                    isFavorite = isFavorite,
                    onEditClick = onEditClick,
                    onFavoriteClick = onFavoriteClick
                )
            }
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
            color = generateRgbColor(),
            isFavorite = isFavorite,
            onEditClick = {},
            onFavoriteClick = { isFavorite = !isFavorite }
        )
    }
}