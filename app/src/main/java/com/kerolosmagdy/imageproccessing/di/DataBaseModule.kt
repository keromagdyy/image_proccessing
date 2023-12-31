package com.kerolosmagdy.imageproccessing.di

import android.app.Application
import androidx.room.Room
import com.kerolosmagdy.imageproccessing.data.db.room.CharactersDao
import com.kerolosmagdy.imageproccessing.data.db.room.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {
    @Singleton
    @Provides
    fun provideNewsDatabase(app: Application): Database {
        return Room.databaseBuilder(app, Database::class.java, "marvel_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideInvoiceDao(database: Database): CharactersDao {
        return database.getCharactersDao()
    }

}