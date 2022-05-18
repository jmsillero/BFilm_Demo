package com.bacteria.bestfilm.data.cache

import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import javax.inject.Inject


class EncryptedPreferences @Inject constructor(private val sharedPreferences: SharedPreferences) {

    var editor = sharedPreferences.edit()

    fun saveToken(token: String) {
        editor.putString("token", token)
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", "")
    }

}