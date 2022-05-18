package com.bacteria.bestfilm.data.remote.datasource

import com.bacteria.bestfilm.data.remote.dto.FilmDto
import com.bacteria.bestfilm.data.remote.dto.FilmListDto
import com.bacteria.bestfilm.data.remote.dto.LoginDto
import com.bacteria.bestfilm.data.remote.dto.LoginResponseDto
import kotlinx.coroutines.flow.Flow


interface RemoteFilmDatasource {
    suspend fun fetchFilms(countryCode: String, cinema: String): Flow<FilmListDto>
}