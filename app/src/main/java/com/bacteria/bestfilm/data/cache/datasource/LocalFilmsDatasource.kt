package com.bacteria.bestfilm.data.cache.datasource

import com.bacteria.bestfilm.data.cache.local.FilmListLocal
import com.bacteria.bestfilm.data.cache.local.FilmLocal
import com.bacteria.bestfilm.data.cache.local.RouteLocal
import com.bacteria.bestfilm.data.remote.dto.FilmListDto
import kotlinx.coroutines.flow.Flow

interface LocalFilmsDatasource {
    suspend fun fetchFilms(): Flow<List<FilmLocal>>

    suspend fun fetchRoutes(): Flow<List<RouteLocal>>

    suspend fun fetchRoutesByCode(code: String): Flow<RouteLocal>

    suspend fun saveFilms(filmListLocal: FilmListLocal)

    suspend fun getFilmById(id: Long): Flow<FilmLocal>
}