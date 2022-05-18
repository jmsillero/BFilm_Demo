package com.bacteria.bestfilm.data.cache.datasource.impl

import com.bacteria.bestfilm.data.cache.FilmsDatabase
import com.bacteria.bestfilm.data.cache.datasource.LocalFilmsDatasource
import com.bacteria.bestfilm.data.cache.local.FilmListLocal
import com.bacteria.bestfilm.data.cache.local.FilmLocal
import com.bacteria.bestfilm.data.cache.local.RouteLocal
import com.bacteria.bestfilm.data.remote.RemoteService
import com.bacteria.bestfilm.data.remote.datasource.RemoteFilmDatasource
import com.bacteria.bestfilm.data.remote.datasource.RemoteUserDatasource
import com.bacteria.bestfilm.data.remote.dto.FilmDto
import com.bacteria.bestfilm.data.remote.dto.FilmListDto
import com.bacteria.bestfilm.data.remote.dto.LoginDto
import com.bacteria.bestfilm.data.remote.dto.LoginResponseDto
import com.bacteria.bestfilm.data.remote.errors.ErrorUtils
import com.bacteria.bestfilm.presentation.model.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import java.net.URLEncoder
import javax.inject.Inject
import kotlin.math.log

class LocalFilmDatastoreImpl @Inject constructor(private val localDatabase: FilmsDatabase) :
    LocalFilmsDatasource {

    override suspend fun fetchFilms(): Flow<List<FilmLocal>> {
        try {
            return localDatabase.filmsDao().getAll()
        } catch (e: Exception) {
            throw e
        }
    }


    override suspend fun fetchRoutes(): Flow<List<RouteLocal>> {
        try {
            return localDatabase.routesDao().getAll()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchRoutesByCode(code: String): Flow<RouteLocal> {
        try {
            val result =  localDatabase.routesDao().getRouteByCode(code)
            return result
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun saveFilms(filmListLocal: FilmListLocal) {
        localDatabase.filmsDao().insertAll(filmListLocal.movies)
        filmListLocal.routes?.let { localDatabase.routesDao().insertAll(it) }
    }

    override suspend fun getFilmById(id: Long): Flow<FilmLocal> {
        try {
            return localDatabase.filmsDao().getFilmById(id)
        } catch (e: Exception) {
            throw e
        }
    }
}