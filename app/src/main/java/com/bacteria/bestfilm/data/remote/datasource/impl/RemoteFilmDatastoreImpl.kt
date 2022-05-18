package com.bacteria.bestfilm.data.remote.datasource.impl

import com.bacteria.bestfilm.data.remote.RemoteService
import com.bacteria.bestfilm.data.remote.datasource.RemoteFilmDatasource
import com.bacteria.bestfilm.data.remote.dto.FilmListDto
import com.bacteria.bestfilm.data.remote.errors.ErrorUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RemoteFilmDatastoreImpl @Inject constructor(private val service: RemoteService) :
    RemoteFilmDatasource {

    override suspend fun fetchFilms(countryCode: String, cinema: String): Flow<FilmListDto> {
        try {
            val filmResults = service.fetchMovies(countryCode, cinema)
            val body = filmResults.body()
            if (filmResults.isSuccessful && body != null) {
                return flow {
                    emit(body)
                }
            }
            return flow {
                ErrorUtils.parseError(filmResults).message()?.let { error(it) }
            }
        } catch (e: Exception) {
            throw e
        }
    }
}