package com.kerolosmagdy.imageproccessing.data.model

import androidx.room.ColumnInfo
import androidx.room.TypeConverters
import com.kerolosmagdy.imageproccessing.data.db.room.ResultListConverter


data class Data(
    val count: Int,
    val limit: Int,
    val offset: Int,
    @ColumnInfo(name = "results")
    @TypeConverters(ResultListConverter::class)
    val results: List<Result>,
    val total: Int
)