package com.niqr.magicbutton.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.coroutines.flow.MutableStateFlow

@Entity(tableName = "magick_color")
data class MagickColor(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo
    val color: ColorWrapper,
    @ColumnInfo
    val isFavorite: MutableStateFlow<Boolean>
)