package com.bacteria.bestfilm.domain.repository

import com.bacteria.bestfilm.domain.entity.FilmEntity
import com.bacteria.bestfilm.domain.entity.FilmListEntity
import com.bacteria.bestfilm.domain.entity.RouteEntity
import kotlinx.coroutines.flow.Flow

interface FilmRepository {
    suspend fun loadFilms(countryCode: String, cinema: String): Flow<FilmListEntity>
    suspend fun getFilmById(id: Long): Flow<FilmEntity>
    suspend fun loadRouteByCode(code: String): Flow<RouteEntity>
}