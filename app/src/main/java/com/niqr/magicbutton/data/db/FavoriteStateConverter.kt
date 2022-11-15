package com.niqr.magicbutton.data.db

import androidx.room.TypeConverter
import kotlinx.coroutines.flow.MutableStateFlow

class FavoriteStateConverter {

    @TypeConverter
    fun toFavoriteStateFlow(state: Boolean?): MutableStateFlow<Boolean>? {
        if (state == null) return null
        return MutableStateFlow(state)
    }

    @TypeConverter
    fun toFavoriteState(stateFlow: MutableStateFlow<Boolean>?): Boolean? {
        if (stateFlow == null) return null
        return stateFlow.value
    }
}