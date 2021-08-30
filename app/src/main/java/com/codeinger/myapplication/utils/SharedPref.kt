package com.codeinger.myapplication.utils

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPref @Inject constructor(private val sharedPreferences: SharedPreferences) {


    fun putToken(key: String, token: String) {
            sharedPreferences.edit().putString(key, token).apply()
    }

    fun getToken(key: String): String {
        return  sharedPreferences.getString(key, "")!!
    }

    fun putLangPref(key: String, token: String) {
        sharedPreferences.edit().putString(key, token).apply()
    }

    fun getLangPref(key: String): String {
        return sharedPreferences.getString(key, "en")!!
    }

    fun putBoolean(key: String, token: Boolean) {
        sharedPreferences.edit().putBoolean(key, token).apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun putInt(key: String, token: Int) {
        sharedPreferences.edit().putInt(key, token).apply()
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

}
