package com.kerolosmagdy.imageproccessing.data.util

import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

object ConstantLinks {
    const val BASE_URL = "https://gateway.marvel.com/v1/public/"     // Prod
    val timestamp = Timestamp(System.currentTimeMillis()).time.toString()
    const val API_KEY = "af335b4ad9e76071eaa5b602c54167d5"
    const val PRIVATE_KEY = "6900c339cabfd8a323a242950734ccb7c8b2bcea"
    const val limit = 50
    const val ITEMS_PER_PAGE: Int = 10
    fun hash(): String {
        val input = "$timestamp$PRIVATE_KEY$API_KEY"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32,'0')
    }

}