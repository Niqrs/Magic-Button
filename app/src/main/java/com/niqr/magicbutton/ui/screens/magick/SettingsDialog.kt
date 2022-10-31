package com.niqr.magicbutton.ui.screens.magick

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.niqr.magicbutton.data.model.MagickColorGenerator
import com.niqr.magicbutton.ui.screens.magick.components.ColorRangeSlider
import com.niqr.magicbutton.ui.theme.MagicButtonTheme
import com.niqr.magicbutton.utils.toRoundedString

@Composable
fun SettingsDialog(
    closeDialog: () -> Unit,
    onSaveSettings: (MagickColorGenerator) -> Unit
) {
    val redSliderPosition = remember {
        mutableStateOf(0f..255f)
    }
    val greenSliderPosition = remember {
        mutableStateOf(0f..255f)
    }
    val blueSliderPosition = remember {
        mutableStateOf(0f..255f)
    }
    AlertDialog(
        onDismissRequest = closeDialog,
        icon = {
            Icon(Icons.Default.Settings, contentDescription = "Generation Settings")
        },
        title = {
            Text(text = "Generation Settings")
        },
        text = {
            Column {
                val redHeader = "RED: ${redSliderPosition.value.toRoundedString()}"
                val greenHeader = "GREEN: ${greenSliderPosition.value.toRoundedString()}"
                val blueHeader = "BLUE: ${blueSliderPosition.value.toRoundedString()}"

                ColorRangeSlider(
                    header = redHeader,
                    value = redSliderPosition.value,
                    onValueChange = { newValue ->
                        redSliderPosition.value = newValue
                    }
                )
                ColorRangeSlider(
                    header = greenHeader,
                    value = greenSliderPosition.value,
                    onValueChange = { newValue ->
                        greenSliderPosition.value = newValue
                    }
                )
                ColorRangeSlider(
                    header = blueHeader,
                    value = blueSliderPosition.value,
                    onValueChange = { newValue ->
                        blueSliderPosition.value = newValue
                    }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onSaveSettings(
                        MagickColorGenerator(
                            redRange = redSliderPosition.value,
                            greenRange = greenSliderPosition.value,
                            blueRange = blueSliderPosition.value
                        )
                    )
                    closeDialog()
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(
                onClick = closeDialog
            ) {
                Text("Dismiss")
            }
        }
    )
}

@Preview
@Composable
private fun SettingsDialogPreview() {
    MagicButtonTheme {
        SettingsDialog(
            closeDialog = {},
            onSaveSettings = {}
        )
    }
}