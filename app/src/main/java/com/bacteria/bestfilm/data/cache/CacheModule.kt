package com.bacteria.bestfilm.data.cache

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.bacteria.bestfilm.data.cache.datasource.LocalFilmsDatasource
import com.bacteria.bestfilm.data.cache.datasource.impl.LocalFilmDatastoreImpl
import com.bacteria.bestfilm.data.cache.preferences.EncryptedPreferences
import com.bacteria.bestfilm.data.cache.preferences.impl.EncryptedPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class CacheModule {
    @Provides
    @Singleton
    fun buildService(@ApplicationContext context: Context): SharedPreferences {
        val masterKey = MasterKey.Builder(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val sharedPreferences = EncryptedSharedPreferences.create(
            context,
            "app_shared_preferences",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
        return sharedPreferences
    }

    @Singleton
    @Provides
    fun provideItemRoomDatabase(@ApplicationContext context: Context): FilmsDatabase {
        return Room.databaseBuilder(context, FilmsDatabase::class.java, "films_database")
            .build()
    }

    @Singleton
    @Provides
    fun provideLocalCache(database: FilmsDatabase): LocalFilmsDatasource {
        return LocalFilmDatastoreImpl(database)
    }

    @Singleton
    @Provides
    fun provideEncryptedPreferences(sharedPreferences: SharedPreferences): EncryptedPreferences {
        return EncryptedPreferencesImpl(sharedPreferences)
    }

}