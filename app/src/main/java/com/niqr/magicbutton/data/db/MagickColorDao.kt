package com.niqr.magicbutton.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.niqr.magicbutton.data.model.MagickColor

@Dao
interface MagickColorDao {
    @Query("SELECT * FROM magick_color WHERE id=:id")
    suspend fun getColorById(id: Int): MagickColor?

    @Query("SELECT * FROM magick_color ORDER BY id DESC LIMIT 1")
    fun getLastColor(): MagickColor?

    @Query("SELECT * FROM magick_color WHERE is_favorite IS 1 ORDER BY id DESC")
    fun getFavoriteColors(): PagingSource<Int, MagickColor>

    @Query("SELECT MAX(Id) FROM magick_color")
    suspend fun getLastId(): Int?

    @Update
    suspend fun updateColor(magickColor: MagickColor)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addColor(magickColor: MagickColor)
}