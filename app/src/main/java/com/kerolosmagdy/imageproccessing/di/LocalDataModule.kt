package com.kerolosmagdy.imageproccessing.di

import com.kerolosmagdy.imageproccessing.data.db.room.CharactersDao
import com.kerolosmagdy.imageproccessing.data.repository.dataSource.CharactersLocalDataSource
import com.kerolosmagdy.imageproccessing.data.repository.dataSourceImpl.CharactersLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideCharactersModelLocalDataSource(charactersModelDao: CharactersDao): CharactersLocalDataSource {
        return CharactersLocalDataSourceImpl(charactersModelDao)
    }

}