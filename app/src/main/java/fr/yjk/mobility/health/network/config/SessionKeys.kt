package fr.yjk.mobility.health.network.config

import android.content.Context
import android.content.SharedPreferences
import java.time.LocalDateTime
import javax.inject.Inject

class SessionKeys @Inject constructor(val context: Context) {

    private val sharedPref: SharedPreferences =
        context.getSharedPreferences("auth_key_store", Context.MODE_PRIVATE)

    fun save(token: String, key: String = "auth_token_key") {
        with(sharedPref.edit()) {
            putString(key, token)
            apply()
        }
    }

    fun saveCurrentDateTime(key: String) {
        with(sharedPref.edit()) {
            putString(key, LocalDateTime.now().minusSeconds(4L).toString())
            apply()
        }
    }

    fun findToken(key: String = "auth_token_key"): String? {
        return sharedPref.getString(key, null)
    }

    fun remove() {
        sharedPref.edit().clear().apply()
    }
}