package com.kerolosmagdy.imageproccessing.data.repository.dataSourceImpl

import com.kerolosmagdy.imageproccessing.data.db.room.CharactersDao
import com.kerolosmagdy.imageproccessing.data.model.CharactersModel
import com.kerolosmagdy.imageproccessing.data.repository.dataSource.CharactersLocalDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharactersLocalDataSourceImpl(private val charactersDao: CharactersDao) :
    CharactersLocalDataSource {
    override suspend fun getCharactersFromDB(): CharactersModel {
        return charactersDao.getCharacters()
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            charactersDao.deleteAllCharacters()
        }
    }

    override suspend fun saveCharactersToDB(character: CharactersModel) {
        CoroutineScope(Dispatchers.IO).launch {
            charactersDao.saveCharacters(character)
        }
    }
}