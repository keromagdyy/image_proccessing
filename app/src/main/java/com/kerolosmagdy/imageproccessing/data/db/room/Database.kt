package com.kerolosmagdy.imageproccessing.data.db.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kerolosmagdy.imageproccessing.data.model.CharactersModel

@Database(
    entities = [CharactersModel::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converter::class, ResultConverter::class, ResultListConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun getCharactersDao(): CharactersDao
}

