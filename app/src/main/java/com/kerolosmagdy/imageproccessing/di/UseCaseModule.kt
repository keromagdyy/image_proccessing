package com.kerolosmagdy.imageproccessing.di

import com.kerolosmagdy.imageproccessing.domain.repository.CharactersRepository
import com.kerolosmagdy.imageproccessing.domain.useCase.characters.GetCharactersFromDbUseCase
import com.kerolosmagdy.imageproccessing.domain.useCase.characters.GetCharactersUseCase
import com.kerolosmagdy.imageproccessing.domain.useCase.characters.SaveCharactersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {


    @Provides
    fun provideGetCharactersFromDbUseCase(charactersRepository: CharactersRepository): GetCharactersFromDbUseCase {
        return GetCharactersFromDbUseCase(charactersRepository)
    }
    @Provides
    fun provideGetCharactersUseCase(charactersRepository: CharactersRepository): GetCharactersUseCase {
        return GetCharactersUseCase(charactersRepository)
    }
    @Provides
    fun provideUpdateCharactersUseCase(charactersRepository: CharactersRepository): SaveCharactersUseCase {
        return SaveCharactersUseCase(charactersRepository)
    }

}