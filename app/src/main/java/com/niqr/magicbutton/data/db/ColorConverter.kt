package com.niqr.magicbutton.data.db

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.graphics.blue
import androidx.core.graphics.green
import androidx.core.graphics.red
import androidx.room.TypeConverter
import com.niqr.magicbutton.data.model.ColorWrapper

class ColorConverter {

    @TypeConverter
    fun toColor(rgb: Int?): ColorWrapper? {
        if (rgb == null) return null
        return ColorWrapper(
            Color(
                red = rgb.red,
                green = rgb.green,
                blue = rgb.blue
            )
        )
    }

    @TypeConverter
    fun colorToInt(color: ColorWrapper?): Int? {
        if (color == null) return null
        return color.color.toArgb()
    }
}