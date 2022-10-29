package com.niqr.magicbutton.di

import com.niqr.magicbutton.data.MagickColorInMemoryRepository
import com.niqr.magicbutton.data.MagickColorRepository
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
    fun provideRepository(): MagickColorRepository = MagickColorInMemoryRepository()
}