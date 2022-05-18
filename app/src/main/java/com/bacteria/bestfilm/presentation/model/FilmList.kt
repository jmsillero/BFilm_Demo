package com.bacteria.bestfilm.presentation.model

import com.bacteria.bestfilm.domain.entity.FilmListEntity

data class FilmList(val movies: MutableList<Film>, val routes: List<Route>?)

fun FilmListEntity.toFilmList(): FilmList {
    return FilmList(movies.map { it.toFilm() } as MutableList<Film>, routes?.map { it.toRoute() })
}