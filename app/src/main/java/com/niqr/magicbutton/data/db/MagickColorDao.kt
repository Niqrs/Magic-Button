package com.niqr.magicbutton.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.niqr.magicbutton.data.model.MagickColor

@Dao
interface MagickColorDao {
    @Query("SELECT * FROM magick_color WHERE Id=:id")
    suspend fun getColorById(id: Long): MagickColor?

    @Query("SELECT * FROM magick_color ORDER BY Id DESC LIMIT 1")
    fun getLastColor(): MagickColor?

    @Query("SELECT MAX(Id) FROM magick_color")
    suspend fun getLastId(): Long?

    @Update
    suspend fun updateColor(magickColor: MagickColor)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addColor(magickColor: MagickColor): Long
}