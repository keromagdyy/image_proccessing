package com.kerolosmagdy.imageproccessing.data.repository.dataSource

import com.kerolosmagdy.imageproccessing.data.model.CharactersModel
import retrofit2.Response

interface CharactersRemoteDataSource {
    suspend fun getCharacters(
        offset: String
    ): Response<CharactersModel>
}