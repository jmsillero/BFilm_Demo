package com.bacteria.bestfilm.data.cache.preferences

interface EncryptedPreferences {
    fun saveToken(token: String)
    fun getToken(): String?
}