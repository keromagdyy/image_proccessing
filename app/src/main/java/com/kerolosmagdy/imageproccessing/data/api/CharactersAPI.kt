package com.kerolosmagdy.imageproccessing.data.api

import com.kerolosmagdy.imageproccessing.data.model.CharactersModel
import com.kerolosmagdy.imageproccessing.data.util.ConstantLinks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersAPI {

    @GET("v1/public/characters")
    suspend fun getCharacters(
        @Query("apiKey") apiKey: String = ConstantLinks.API_KEY,
        @Query("ts") ts: String = ConstantLinks.timestamp,
        @Query("hash") hash: String = ConstantLinks.hash(),
        @Query("offset") offset: String,
    ): Response<CharactersModel>

}