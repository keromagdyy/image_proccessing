package com.kerolosmagdy.imageproccessing.data.repository.dataSource

import com.kerolosmagdy.imageproccessing.data.model.CharactersModel


interface CharactersCacheDataSource {
    suspend fun getCharactersFromCache(offset: String): CharactersModel
    suspend fun saveCharactersToCache(character: CharactersModel)
}