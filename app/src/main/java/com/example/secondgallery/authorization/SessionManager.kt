package com.example.secondgallery.authorization

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {

    // TODO shared preferences можно провайдить через DI
    private var prefs: SharedPreferences =
        context.getSharedPreferences("secondGallery", Context.MODE_PRIVATE)

    companion object {
        const val USER_TOKEN = "user_token"
    }

    fun saveAuthToken(token: String) {
        val editor = prefs.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }
}