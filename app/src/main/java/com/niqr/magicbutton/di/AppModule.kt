package com.niqr.magicbutton.di

import com.niqr.magicbutton.data.datastore.StoreColorGenerationPreferences
import com.niqr.magicbutton.data.repository.MagickColorInMemoryRepository
import com.niqr.magicbutton.data.repository.MagickColorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideRepository(
        storeColorGenerationPreferences: StoreColorGenerationPreferences
    ): MagickColorRepository = MagickColorInMemoryRepository(storeColorGenerationPreferences)
}