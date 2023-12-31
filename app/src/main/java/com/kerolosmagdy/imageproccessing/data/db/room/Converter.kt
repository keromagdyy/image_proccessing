package com.kerolosmagdy.imageproccessing.data.db.room

import androidx.room.TypeConverter
import com.kerolosmagdy.imageproccessing.data.model.Data
import com.google.gson.Gson

class Converter {
    val gson= Gson()

    @TypeConverter
    fun fromDataToString(data: Data?): String? {
        // Convert Data object to JSON string
        return gson.toJson(data)
    }

    @TypeConverter
    fun fromStringToData(string: String?): Data? {
        // Convert JSON string to Data object
        return gson.fromJson(string, Data::class.java)
    }

}