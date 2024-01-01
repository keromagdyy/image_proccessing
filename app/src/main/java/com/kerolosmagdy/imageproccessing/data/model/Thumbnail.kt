package com.kerolosmagdy.imageproccessing.data.model

import android.net.Uri
import java.io.Serializable

data class Thumbnail(
    val extension: String = "",
    val path: String = "",
    val localPath: String? = null // Add this field for local storage path
) :Serializable