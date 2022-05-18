package com.bacteria.bestfilm.data.remote

import com.bacteria.bestfilm.data.remote.dto.FilmDto
import com.bacteria.bestfilm.data.remote.dto.FilmListDto
import com.bacteria.bestfilm.data.remote.dto.LoginDto
import com.bacteria.bestfilm.data.remote.dto.LoginResponseDto
import com.bacteria.bestfilm.presentation.model.Film
import retrofit2.Response
import retrofit2.http.*

interface RemoteService {
    @POST("oauth/token")
    @Headers("Content-Type: text/plain")
    suspend fun login(
        @Body() loginDto: String
    ): Response<LoginResponseDto>

    @GET("movies")
    suspend fun fetchMovies(
        @Query("country_code") countryCode: String,
        @Query("cinema") cinema: String
    ): Response<FilmListDto>
}