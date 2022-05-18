package com.bacteria.bestfilm.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bacteria.bestfilm.data.cache.local.FilmLocal
import com.bacteria.bestfilm.data.cache.local.RouteLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface RoutesDao {
    @Query("SELECT * FROM RouteLocal")
    fun getAll(): Flow<List<RouteLocal>>

    @Query("SELECT * FROM RouteLocal WHERE code = :code LIMIT 1")
    fun getRouteByCode(code: String): Flow<RouteLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<RouteLocal>)
}