package com.kerolosmagdy.imageproccessing.di

import com.kerolosmagdy.imageproccessing.data.repository.CharactersRepositoryImpl
import com.kerolosmagdy.imageproccessing.data.repository.dataSource.CharactersCacheDataSource
import com.kerolosmagdy.imageproccessing.data.repository.dataSource.CharactersLocalDataSource
import com.kerolosmagdy.imageproccessing.data.repository.dataSource.CharactersRemoteDataSource
import com.kerolosmagdy.imageproccessing.domain.repository.CharactersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCharactersRepository(
        CharactersRemoteDataSource: CharactersRemoteDataSource,
        CharactersLocalDataSource: CharactersLocalDataSource,
        CharactersCacheDataSource: CharactersCacheDataSource
    ): CharactersRepository {

        return CharactersRepositoryImpl(
            CharactersCacheDataSource,
            CharactersLocalDataSource,
            CharactersRemoteDataSource
        )
    }

}