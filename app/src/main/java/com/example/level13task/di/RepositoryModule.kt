package com.example.level13task.di

import com.example.level13task.data.repository.PhotoRepositoryImpl
import com.example.level13task.domain.repository.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun providePhotoRepository(repository: PhotoRepositoryImpl): PhotoRepository
}