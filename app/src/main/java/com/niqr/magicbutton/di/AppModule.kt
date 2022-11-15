package com.niqr.magicbutton.di

import android.content.Context
import androidx.room.Room
import com.niqr.magicbutton.data.datastore.StoreColorGenerationPreferences
import com.niqr.magicbutton.data.db.MagickColorDao
import com.niqr.magicbutton.data.db.MagickColorDatabase
import com.niqr.magicbutton.data.repository.MagickColorDatabaseRepository
import com.niqr.magicbutton.data.repository.MagickColorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    fun provideMagickColorDb(
        @ApplicationContext context: Context
    ): MagickColorDatabase = Room.databaseBuilder(
        context.applicationContext,
        MagickColorDatabase::class.java,
        "magick_color_database"
    ).build()

    @Provides
    fun provideMagickColorDao(
        magickColorDatabase: MagickColorDatabase
    ): MagickColorDao = magickColorDatabase.magickColorDao()

    @Provides
    fun provideRepository(
        storeColorGenerationPreferences: StoreColorGenerationPreferences,
        magickColorDao: MagickColorDao
    ): MagickColorRepository = MagickColorDatabaseRepository(
        storeColorGenerationPreferences = storeColorGenerationPreferences,
        magickColorDao = magickColorDao
    )
}