package com.bacteria.bestfilm.data.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bacteria.bestfilm.data.cache.dao.FilmDao
import com.bacteria.bestfilm.data.cache.dao.RoutesDao
import com.bacteria.bestfilm.data.cache.local.Converters
import com.bacteria.bestfilm.data.cache.local.FilmLocal
import com.bacteria.bestfilm.data.cache.local.RouteLocal

@Database(entities = [FilmLocal::class, RouteLocal::class], version = 1)
@TypeConverters(Converters::class)
abstract class FilmsDatabase : RoomDatabase() {
    abstract fun filmsDao(): FilmDao
    abstract fun routesDao(): RoutesDao
}