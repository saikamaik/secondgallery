package com.example.secondgallery.authorization

import android.content.Context
import android.content.SharedPreferences
import com.example.secondgallery.utils.Const.ACCESS_TOKEN
import com.example.secondgallery.utils.Const.CLIENT_ID
import com.example.secondgallery.utils.Const.CLIENT_SECRET
import com.example.secondgallery.utils.Const.USER_ID
import com.example.secondgallery.utils.Const.USER_TOKEN
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
    val context: Context,
    private val prefs: SharedPreferences
) {
    fun saveId(id: Int) {
        val editor = prefs.edit()
        editor?.putString(USER_ID, id.toString())
        editor?.apply()
    }

    fun saveClientInfo(id: String, clientSecret: String) {
        val editor = prefs.edit()
        editor?.putString(CLIENT_ID, id)
        editor?.putString(CLIENT_SECRET, clientSecret)
        editor?.apply()
    }

    fun saveAccessToken(access_token: String?) {
        val editor = prefs.edit()
        editor?.putString(ACCESS_TOKEN, access_token)
        editor?.apply()
    }

    fun saveAuthToken(token: String?) {
        val editor = prefs.edit()
        editor?.putString(USER_TOKEN, token)
        editor?.apply()
    }

    fun fetchClientId(): String? {
        return prefs.getString(CLIENT_ID, null)
    }

    fun fetchClientSecret(): String? {
        return prefs.getString(CLIENT_SECRET, null)
    }

    fun fetchRefreshToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    fun fetchAuthToken(): String? {
        return prefs.getString(ACCESS_TOKEN, null)
    }
}