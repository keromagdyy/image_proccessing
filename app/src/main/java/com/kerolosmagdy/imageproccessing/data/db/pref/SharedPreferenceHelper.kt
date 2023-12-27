package com.kerolosmagdy.imageproccessing.data.db.pref

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceHelper {

    private const val mySharedPreferenceName = "NattingHub4U"
    private var mAppContext: Context? = null
    private const val mySharedPreference_token = "token"
    private const val mySharedPreference_logged = "isLogged"
    private const val mySharedPreference_language = "language"

    fun init(appContext: Context?) {
        mAppContext = appContext
    }

    private val sharedPreferences: SharedPreferences
        get() = mAppContext!!.getSharedPreferences(
            mySharedPreferenceName,
            Context.MODE_PRIVATE
        )
    var userToken: String?
        get() = sharedPreferences.getString(mySharedPreference_token, "")
        set(token) {
            val editor = sharedPreferences.edit()
            editor.putString(mySharedPreference_token, token).apply()
        }
    var language: String?
        get() = sharedPreferences.getString(mySharedPreference_language, "en")
        set(language) {
            val editor = sharedPreferences.edit()
            editor.putString(mySharedPreference_language, language).apply()
        }
    var isLogged: Boolean?
        get() = sharedPreferences.getBoolean(mySharedPreference_logged, false)
        set(loged) {
            val editor = sharedPreferences.edit()
            editor.putBoolean(mySharedPreference_logged, loged!!).apply()
        }
}