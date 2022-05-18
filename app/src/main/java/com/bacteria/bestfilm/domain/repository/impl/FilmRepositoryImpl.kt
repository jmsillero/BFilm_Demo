package com.bacteria.bestfilm.domain.repository.impl

import com.bacteria.bestfilm.data.cache.datasource.LocalFilmsDatasource
import com.bacteria.bestfilm.data.cache.local.toLocal
import com.bacteria.bestfilm.data.remote.datasource.RemoteFilmDatasource
import com.bacteria.bestfilm.domain.entity.FilmEntity
import com.bacteria.bestfilm.domain.entity.FilmListEntity
import com.bacteria.bestfilm.domain.entity.RouteEntity
import com.bacteria.bestfilm.domain.repository.FilmRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class FilmRepositoryImpl @Inject constructor(
    private val remoteFilmDatasource: RemoteFilmDatasource,
    private val localFilmsDatasource: LocalFilmsDatasource
) : FilmRepository {
    override suspend fun loadFilms(countryCode: String, cinema: String): Flow<FilmListEntity> {
        val response = remoteFilmDatasource.fetchFilms(countryCode, cinema)
        return response.map { resp ->
            resp.toEntity()
        }.onEach {
            localFilmsDatasource.saveFilms(it.toLocal())
        }
    }

    override suspend fun getFilmById(id: Long): Flow<FilmEntity> {
        return localFilmsDatasource.getFilmById(id).map {
            it.toEntity()
        }
    }

    override suspend fun loadRouteByCode(code: String): Flow<RouteEntity> {
        return localFilmsDatasource.fetchRoutesByCode(code).map {
            println(it)
            it.toEntity()
        }
    }


}