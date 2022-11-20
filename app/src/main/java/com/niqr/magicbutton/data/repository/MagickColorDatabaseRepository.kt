package com.niqr.magicbutton.data.repository

import androidx.paging.PagingSource
import com.niqr.magicbutton.data.datastore.StoreColorGenerationPreferences
import com.niqr.magicbutton.data.db.MagickColorDao
import com.niqr.magicbutton.data.model.ColorWrapper
import com.niqr.magicbutton.data.model.MagickColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class MagickColorDatabaseRepository @Inject constructor(
    storeColorGenerationPreferences: StoreColorGenerationPreferences,
    private val magickColorDao: MagickColorDao
) : MagickColorRepository, ColorGeneratorImpl(storeColorGenerationPreferences) {

    private val lastColor = MutableStateFlow<MagickColor?>(null)
    init {
        CoroutineScope(Dispatchers.IO).launch {
            lastColor.update {
                magickColorDao.getLastColor()
            }
        }
    }

    override suspend fun generateColor() {
        val magickColor = MagickColor(
            id = 0,
            color = ColorWrapper(colorGenerator.value.generateColor()),
            isFavorite = MutableStateFlow(false)
        )
        magickColorDao.addColor(magickColor)
    }

    override suspend fun magickColor(id: Int): List<MagickColor> {
        val color = magickColorDao.getColorById(id)!!
        if ((lastColor.value?.id ?: -1) <= id)
            lastColor.update {color}
        return listOf(color)
    }

    override fun lastMagickColor(): Flow<MagickColor?> {
        return lastColor
    }

    override fun favoriteColors(): PagingSource<Int, MagickColor> {
        return magickColorDao.getFavoriteColors()
    }

    override suspend fun lastId(): Int {
        return magickColorDao.getLastId() ?: -1
    }

    override suspend fun updateMagickColor(magickColor: MagickColor) {
        magickColorDao.updateColor(magickColor)
    }
}