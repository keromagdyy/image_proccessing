package com.kerolosmagdy.imageproccessing.data.api

import com.kerolosmagdy.imageproccessing.data.model.CharactersModel
import com.kerolosmagdy.imageproccessing.data.util.ConstantLinks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersAPI {

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int = ConstantLinks.limit,
        @Query("offset") offset: String,
    ): Response<CharactersModel>

}