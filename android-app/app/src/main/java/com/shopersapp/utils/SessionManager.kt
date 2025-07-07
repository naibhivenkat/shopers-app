package com.shopers.app

import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "ShopersAppPrefs"
    private const val KEY_USER_ID = "user_id"
    private const val KEY_ROLE = "role"

    fun saveSession(context: Context, userId: Int, role: String) {
        val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt(KEY_USER_ID, userId)
            putString(KEY_ROLE, role)
            apply()
        }
    }

    fun getUserId(context: Context): Int {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getInt(KEY_USER_ID, 0)
    }

    fun getUserRole(context: Context): String? {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(KEY_ROLE, null)
    }

    fun clear(context: Context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit().clear().apply()
    }
}
