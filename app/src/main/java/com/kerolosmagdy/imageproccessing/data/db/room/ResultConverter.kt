package com.kerolosmagdy.imageproccessing.data.db.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.kerolosmagdy.imageproccessing.data.model.Result

class ResultConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromResultToString(result: Result?): String? {
        return gson.toJson(result)
    }

    @TypeConverter
    fun fromStringToResult(string: String?): Result? {
        return gson.fromJson(string, Result::class.java)
    }
}
