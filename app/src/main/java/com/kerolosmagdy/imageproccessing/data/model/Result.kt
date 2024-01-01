package com.kerolosmagdy.imageproccessing.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import com.kerolosmagdy.imageproccessing.data.db.room.ThumbnailConverter
import java.io.Serializable

@TypeConverters(ThumbnailConverter::class)
data class Result(
    var description: String,
    val id: Int,
    val modified: String,
    val name: String,
    @ColumnInfo(name = "thumbnail")
    @TypeConverters(ThumbnailConverter::class)
    val thumbnail: Thumbnail,
): Serializable