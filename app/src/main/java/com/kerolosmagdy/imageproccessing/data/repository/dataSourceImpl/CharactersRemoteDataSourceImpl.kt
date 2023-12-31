package com.kerolosmagdy.imageproccessing.data.repository.dataSourceImpl

import android.util.Log
import com.kerolosmagdy.imageproccessing.data.api.CharactersAPI
import com.kerolosmagdy.imageproccessing.data.model.CharactersModel
import com.kerolosmagdy.imageproccessing.data.repository.dataSource.CharactersRemoteDataSource
import com.kerolosmagdy.imageproccessing.data.util.Common
import retrofit2.Response

class CharactersRemoteDataSourceImpl(
   private val charactersAPI: CharactersAPI
) : CharactersRemoteDataSource {
    override suspend fun getCharacters(
        offset: String
    ): Response<CharactersModel> {
        Log.d(Common.KeroDebug, "getBrand: ${charactersAPI.getCharacters(offset = "0")}")
        return charactersAPI.getCharacters(offset = offset)

    }
}

