package com.kerolosmagdy.imageproccessing.domain.useCase.characters

import com.kerolosmagdy.imageproccessing.data.model.CharactersModel
import com.kerolosmagdy.imageproccessing.domain.repository.CharactersRepository

class GetCharactersUseCase(private val charactersRepository: CharactersRepository) {

    suspend fun execute(offset:String): CharactersModel {
        return charactersRepository.getCharacters(offset)
    }
}

