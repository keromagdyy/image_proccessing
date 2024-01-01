package com.kerolosmagdy.imageproccessing.data.model

import java.io.Serializable

data class Thumbnail(
    val extension: String = "",
    val path: String = "",
    val localPath: String = "" // Add this field for local storage path
) :Serializable