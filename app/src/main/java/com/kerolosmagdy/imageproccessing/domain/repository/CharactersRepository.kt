package com.kerolosmagdy.imageproccessing.domain.repository

import com.kerolosmagdy.imageproccessing.data.model.CharactersModel

interface CharactersRepository {
    suspend fun getCharacters(offset :String):CharactersModel
    suspend fun getCharactersFromDB(offset :String):CharactersModel
    suspend fun saveCharacters(offset :String):CharactersModel

}