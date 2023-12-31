package com.kerolosmagdy.imageproccessing.shared

sealed class Resource<T>(
    val attributionHTML: String? = null,
    val attributionText: String? = null,
    val code: Int? = null,
    val copyright: String? = null,
    val data: T? = null,
    val etag: String? = null,
    val status: String? = null,
) {
//    class Success<T>(data: T) : Resource<T>(data)
//    class Loading<T>(data: T? = null) : Resource<T>(data)
//    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
}