package com.niqr.magicbutton.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.niqr.magicbutton.data.model.MagickColorGenerator
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class StoreColorGenerationPreferences @Inject constructor(@ApplicationContext val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("ColorGenerationPreferences")
        val COLOR_GENERATION_PREFERENCES_KEY = stringPreferencesKey("color_generation_preferences")
        fun defaultGenerator() = MagickColorGenerator()
    }

    val getPreferences: Flow<MagickColorGenerator> = context.dataStore.data
        .map { preferences ->
            preferences[COLOR_GENERATION_PREFERENCES_KEY]?.let {
                Json.decodeFromString<MagickColorGenerator>(it)
            } ?: MagickColorGenerator()
        }

    suspend fun savePreferences(magickColorGenerator: MagickColorGenerator) {
        context.dataStore.edit { preferences ->
            preferences[COLOR_GENERATION_PREFERENCES_KEY] = Json.encodeToString(magickColorGenerator)
        }
    }
}

