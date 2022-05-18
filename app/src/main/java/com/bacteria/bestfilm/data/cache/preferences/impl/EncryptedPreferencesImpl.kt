package com.bacteria.bestfilm.data.cache.preferences.impl

import android.content.SharedPreferences
import com.bacteria.bestfilm.data.cache.preferences.EncryptedPreferences
import javax.inject.Inject


class EncryptedPreferencesImpl @Inject constructor(private val sharedPreferences: SharedPreferences) :
    EncryptedPreferences {

    var editor = sharedPreferences.edit()
    override fun saveToken(token: String) {
        editor.putString("token", token)
        editor.commit()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString("token", "")
    }


}