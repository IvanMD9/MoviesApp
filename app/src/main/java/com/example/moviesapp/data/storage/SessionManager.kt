package com.example.moviesapp.data.storage

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

private const val NEW_TOKEN = "new_token"
private const val SESSION_ID = "session_id"

class SessionManager @Inject constructor(context: Context) {
    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(NAME, Context.MODE_PRIVATE)

    fun saveNewToken(newToken : String) {
        sharedPrefs.edit().putString(NEW_TOKEN, newToken).apply()
    }

    fun getNewToken(): String? {
        return sharedPrefs.getString(NEW_TOKEN, null)
    }

    fun saveSessionId(sessionId : String) {
        sharedPrefs.edit().putString(SESSION_ID, sessionId).apply()
    }

    fun getSessionId(): String? {
        return sharedPrefs.getString(SESSION_ID, null)
    }

    companion object {
        const val NAME = "movie_app"
    }
}