package com.kerolosmagdy.imageproccessing.data.repository

import android.util.Log
import com.kerolosmagdy.imageproccessing.data.repository.dataSource.CharactersCacheDataSource
import com.kerolosmagdy.imageproccessing.data.repository.dataSource.CharactersLocalDataSource
import com.kerolosmagdy.imageproccessing.data.repository.dataSource.CharactersRemoteDataSource
import com.kerolosmagdy.imageproccessing.data.model.CharactersModel
import com.kerolosmagdy.imageproccessing.domain.repository.CharactersRepository
import java.lang.Exception

class CharactersRepositoryImpl(
    private val charactersCacheDataSource: CharactersCacheDataSource,
    private val charactersLocalDataSource: CharactersLocalDataSource,
    private val charactersRemoteDataSource: CharactersRemoteDataSource,
) : CharactersRepository {

    override suspend fun getCharacters(offset: String): CharactersModel {
        var charactersModel: CharactersModel? = CharactersModel()


        try {
            if (charactersRemoteDataSource.getCharacters(offset).isSuccessful) {
                val body = charactersRemoteDataSource.getCharacters(offset).body()
                if (body != null) {
                    charactersModel = body
                    charactersLocalDataSource.clearAll()
                    charactersLocalDataSource.saveCharactersToDB(charactersModel)
                }

            }

        } catch (exception: Exception) {
            Log.d("MyTag", "getBrandFromAPI: $exception")
        }

        if (charactersModel != null) {
            return charactersModel
            Log.d("kmdvkdkvnkv", "not null: ${charactersModel}")
        } else {
            return getCharactersFromDB(offset)
            Log.d("kmdvkdkvnkv", "null: ${charactersModel}")
        }

    }

    override suspend fun getCharactersFromDB(offset: String): CharactersModel {
        Log.d("kmdvkdkvnkv", "getCharactersFromDB: ${getCharactersFromCache(offset)}")

        return getCharactersFromCache(offset)

    }


    override suspend fun saveCharacters(offset: String): CharactersModel {
        val newListBrand = getCharacters(offset)
        charactersLocalDataSource.clearAll()
        charactersLocalDataSource.saveCharactersToDB(newListBrand)
        charactersCacheDataSource.saveCharactersToCache(newListBrand)

        return newListBrand
    }


    private suspend fun getCharactersFromCache(offset: String): CharactersModel {
        lateinit var characters: CharactersModel

        try {
            characters = charactersCacheDataSource.getCharactersFromCache(offset)
        } catch (exception: Exception) {
            Log.d("MyTag", "getBrandsListFromCache: $exception")
        }

        if (characters != null) {
            return characters
        } else {
            characters = getBrandFromDB()
            charactersCacheDataSource.saveCharactersToCache(characters)
        }
        return characters

    }


    private suspend fun getBrandFromDB(): CharactersModel {
        var charactersModel: CharactersModel? = null

        try {
            charactersModel = charactersLocalDataSource.getCharactersFromDB()
            charactersLocalDataSource.saveCharactersToDB(charactersModel)

        } catch (exception: Exception) {
            Log.d("MyTag", "getCharactersFromDB: $exception")
        }


        return charactersModel!!


    }
}