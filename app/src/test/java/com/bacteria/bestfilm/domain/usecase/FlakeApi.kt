package com.bacteria.bestfilm.domain.usecase

import com.bacteria.bestfilm.data.cache.datasource.LocalFilmsDatasource
import com.bacteria.bestfilm.data.cache.local.FilmListLocal
import com.bacteria.bestfilm.data.cache.local.FilmLocal
import com.bacteria.bestfilm.data.cache.local.RouteLocal
import com.bacteria.bestfilm.data.cache.local.toLocal
import com.bacteria.bestfilm.data.remote.datasource.RemoteFilmDatasource
import com.bacteria.bestfilm.data.remote.dto.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.Instant
import java.time.LocalDate
import java.util.*

val items = mutableListOf<FilmDto>()

val film = FilmDto(
    12, "asdf",
    listOf(MediaDto("asdf", "postersd", "asdfsdf")),
    listOf(23),
    1,
    listOf("asdfasdf", "asdfasdf"),
    "asdfasdf",
    "asdfasdfasdf",
    "asdfasdf",
    Date.from(Instant.parse("2022-05-18T18:35:24.00Z")),

    "asdfasdf",
    "asdfasdf",
    "asdfasdf",

    )

val route = RouteDto("postersd", SizeDto("asdfasdf", "asdfasdf", "asdfasdf"))
val routes = listOf(route)


val fakeResponse = FilmListDto(
    movies = listOf(film),
    routes = routes
)


class FakeFilmRemoteDataSource(
    private val fakeObj: FilmListDto = fakeResponse,
    private val delay: Long = 0
) : RemoteFilmDatasource {

    override suspend fun fetchFilms(countryCode: String, cinema: String): Flow<FilmListDto> {
        delay(delay)
        return flow {
            emit(fakeObj)
        }
    }
}

class FakeFilmLocalDataSource(
    private val fakeObj: FilmListDto = fakeResponse,
    private val delay: Long = 0
) : LocalFilmsDatasource {


    override suspend fun fetchFilms(): Flow<List<FilmLocal>> {
        delay(delay)
        return flow {
            emit(fakeObj.toEntity().toLocal().movies)
        }
    }

    override suspend fun fetchRoutes(): Flow<List<RouteLocal>> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchRoutesByCode(code: String): Flow<RouteLocal> {
        TODO("Not yet implemented")
    }

    override suspend fun saveFilms(filmListLocal: FilmListLocal) {

    }

    override suspend fun getFilmById(id: Long): Flow<FilmLocal> {
        TODO("Not yet implemented")
    }
}