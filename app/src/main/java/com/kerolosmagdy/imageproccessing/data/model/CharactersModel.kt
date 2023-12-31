package com.kerolosmagdy.imageproccessing.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.kerolosmagdy.imageproccessing.data.db.room.Converter
import com.kerolosmagdy.imageproccessing.data.db.room.ResultConverter

@Entity(tableName = "characters")
@TypeConverters(Converter::class, ResultConverter::class)
data class CharactersModel(
    @PrimaryKey val id: Int = 0,
    val attributionHTML: String? = null,
    val attributionText: String? = null,
    val code: Int? = null,
    val copyright: String? = null,
    @TypeConverters(Converter::class)
    @Embedded
    val `data`: Data? = null,
    @TypeConverters(ResultConverter::class)
    val result: Result? = null,
    val etag: String? = null,
    val status: String? = null,
)
