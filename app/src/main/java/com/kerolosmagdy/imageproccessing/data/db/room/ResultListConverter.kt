package com.kerolosmagdy.imageproccessing.data.db.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kerolosmagdy.imageproccessing.data.model.Result

class ResultListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromResultListToString(results: List<Result>?): String? {
        // Convert List<Result> object to JSON string
        return gson.toJson(results)
    }

    @TypeConverter
    fun fromStringToResultList(string: String?): List<Result>? {
        // Convert JSON string to List<Result> object
        val type = object : TypeToken<List<Result>>() {}.type
        return gson.fromJson(string, type)
    }
}
