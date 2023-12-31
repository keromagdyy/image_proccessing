package com.kerolosmagdy.imageproccessing.data.repository.dataSourceImpl

import com.kerolosmagdy.imageproccessing.data.model.CharactersModel
import com.kerolosmagdy.imageproccessing.data.repository.dataSource.CharactersCacheDataSource

class CharactersCacheDataSourceImpl : CharactersCacheDataSource {
    private var charactersModel = CharactersModel()
    override suspend fun getCharactersFromCache(offset: String): CharactersModel {
        return charactersModel
    }

    override suspend fun saveCharactersToCache(character: CharactersModel) {
        charactersModel = character

    }
}