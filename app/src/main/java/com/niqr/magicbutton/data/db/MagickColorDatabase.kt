package com.niqr.magicbutton.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.niqr.magicbutton.data.model.MagickColor

@Database(entities = [MagickColor::class], version = 1, exportSchema = false)
@TypeConverters(ColorConverter::class, FavoriteStateConverter::class)
abstract class MagickColorDatabase : RoomDatabase() {
    abstract fun magickColorDao(): MagickColorDao
}