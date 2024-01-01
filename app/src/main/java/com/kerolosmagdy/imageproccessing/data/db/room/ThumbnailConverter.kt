package com.kerolosmagdy.imageproccessing.data.db.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.kerolosmagdy.imageproccessing.data.model.Thumbnail

class ThumbnailConverter {
    val gson= Gson()

    @TypeConverter
    fun fromThumbnailToString(data: Thumbnail?): String? {
        // Convert Thumbnail object to JSON string
        return gson.toJson(data)
    }

    @TypeConverter
    fun fromStringToThumbnail(string: String?): Thumbnail? {
        // Convert JSON string to Thumbnail object
        return gson.fromJson(string, Thumbnail::class.java)
    }
}
