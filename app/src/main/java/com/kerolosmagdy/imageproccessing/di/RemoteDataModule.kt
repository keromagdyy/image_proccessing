package com.kerolosmagdy.imageproccessing.di

import com.kerolosmagdy.imageproccessing.data.api.CharactersAPI
import com.kerolosmagdy.imageproccessing.data.repository.dataSource.CharactersRemoteDataSource
import com.kerolosmagdy.imageproccessing.data.repository.dataSourceImpl.CharactersRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideBrandRemoteDataSource(
        aPIService: CharactersAPI
    ): CharactersRemoteDataSource {
        return CharactersRemoteDataSourceImpl(aPIService)
    }
}