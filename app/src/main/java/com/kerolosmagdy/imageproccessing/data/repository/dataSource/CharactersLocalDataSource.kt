package com.kerolosmagdy.imageproccessing.data.repository.dataSource

import com.kerolosmagdy.imageproccessing.data.model.CharactersModel

interface CharactersLocalDataSource {

    suspend fun getCharactersFromDB(): CharactersModel
    suspend fun saveCharactersToDB(character: CharactersModel)
    suspend fun clearAll()

}