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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.niqr.magicbutton.data.datastore.StoreColorGenerationPreferences
import com.niqr.magicbutton.ui.model.MagickColorUiState
import com.niqr.magicbutton.ui.theme.MagicButtonTheme
import com.niqr.magicbutton.utils.toRgbString
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun MagickColorItem(
    magickColor: MagickColorUiState,
    onCopyClick: (MagickColorUiState) -> Unit,
    onFavoriteClick: (MagickColorUiState) -> Unit
) {
    val isFavorite by magickColor.isFavorite.collectAsState()

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
        color = magickColor.color
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(start = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "#${magickColor.color.toRgbString()}")
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
                    onCopyClick = {
                        onCopyClick(magickColor)
                    },
                    onFavoriteClick = {
                        onFavoriteClick(magickColor)
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun MagickColorItemPreview() {
    val generator = StoreColorGenerationPreferences.defaultGenerator()
    val magickColor = MagickColorUiState(
        id = 0,
        color = generator.generateColor(),
        MutableStateFlow(false)
    )
    MagicButtonTheme {
        MagickColorItem(
            magickColor = magickColor,
            onCopyClick = {},
            onFavoriteClick = { i -> i.isFavorite.value = !i.isFavorite.value}
        )
    }
}