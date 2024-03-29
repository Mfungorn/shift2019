package com.example.app.core

import android.content.SharedPreferences
import com.example.app.core.model.User
import com.google.gson.Gson
import org.json.JSONObject

class PreferencesApi {
    companion object {
        const val sharedPreferencesName = "tripple.me.prefsTags"

        enum class PrefNames { USER, TOKEN }

        fun getJwt(prefs: SharedPreferences): String? {
            /*
            val gson = Gson()
            val jsonRoot = JSONObject(prefs.getString(Companion.PrefNames.USER.username, null))

            return gson.fromJson(jsonRoot.toString(), User::class.java).login
            */
            return  prefs.getString(Companion.PrefNames.TOKEN.name, null)
        }

        fun setJwt(prefs: SharedPreferences, token: String) {
            prefs.edit().putString(Companion.PrefNames.TOKEN.name, token).apply()
        }

        fun setUser(prefs: SharedPreferences, user: User) {
            val gson = Gson()
            val json = gson.toJson(user)
            prefs.edit().putString(Companion.PrefNames.USER.name, json).apply()
        }

        fun getUser(prefs: SharedPreferences): User? {
            val gson = Gson()
            val jsonString = prefs.getString(Companion.PrefNames.USER.name, null)
            if (jsonString.isNullOrBlank()) return null

            val jsonRoot = JSONObject(jsonString)
            return gson.fromJson(jsonRoot.toString(), User::class.java)
        }

        fun delData(prefs: SharedPreferences) {
            prefs.edit()
                .clear()
                .apply()
        }
    }
}