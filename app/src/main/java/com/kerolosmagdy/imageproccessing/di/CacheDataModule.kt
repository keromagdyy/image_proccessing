package com.kerolosmagdy.imageproccessing.di

import com.kerolosmagdy.imageproccessing.data.repository.dataSource.CharactersCacheDataSource
import com.kerolosmagdy.imageproccessing.data.repository.dataSourceImpl.CharactersCacheDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CacheDataModule {

    @Singleton
    @Provides
    fun provideBrandCacheDataSource(

    ): CharactersCacheDataSource {
        return CharactersCacheDataSourceImpl()
    }
}