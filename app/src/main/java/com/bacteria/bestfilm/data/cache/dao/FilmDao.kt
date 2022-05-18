package com.bacteria.bestfilm.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bacteria.bestfilm.data.cache.local.FilmLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("SELECT * FROM FilmLocal")
    fun getAll(): Flow<List<FilmLocal>>

    @Query("SELECT * FROM FilmLocal WHERE id = :id")
    fun getFilmById(id: Long): Flow<FilmLocal>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<FilmLocal>)
}