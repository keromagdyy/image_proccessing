package com.kerolosmagdy.imageproccessing.di

import android.app.Application
import com.kerolosmagdy.imageproccessing.domain.useCase.characters.GetCharactersFromDbUseCase
import com.kerolosmagdy.imageproccessing.domain.useCase.characters.GetCharactersUseCase
import com.kerolosmagdy.imageproccessing.domain.useCase.characters.SaveCharactersUseCase
import com.kerolosmagdy.imageproccessing.presentation.ui.listingImages.CharactersViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideCharactersViewModelFactory(
        application: Application,
        getCharactersUseCase: GetCharactersUseCase,
        saveCharactersUseCase: SaveCharactersUseCase,
        getCharactersFromDbUseCase: GetCharactersFromDbUseCase
    ): CharactersViewModelFactory {
        return CharactersViewModelFactory(
            application,
            getCharactersUseCase,
            saveCharactersUseCase,
            getCharactersFromDbUseCase

        )
    }

}








