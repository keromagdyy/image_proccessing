package com.kerolosmagdy.imageproccessing.data.db.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kerolosmagdy.imageproccessing.data.model.CharactersModel

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characters: CharactersModel)

    @Query("SELECT * FROM characters")
    suspend fun getCharacters():CharactersModel

    @Query("DELETE FROM characters")
    suspend fun deleteAllCharacters()
}