package com.bacteria.bestfilm.data.cache.local

import com.bacteria.bestfilm.domain.entity.FilmEntity
import com.bacteria.bestfilm.domain.entity.FilmListEntity
import com.google.gson.annotations.SerializedName

class FilmListLocal(
    val movies: List<FilmLocal>,
    val routes: List<RouteLocal>?
) {
    fun toEntity(): FilmListEntity {
        return FilmListEntity(movies.map { it.toEntity() }, routes?.map { it.toEntity() })
    }
}

fun FilmListEntity.toLocal(): FilmListLocal {
    return FilmListLocal(
        movies.map { it.toLocal() },
        routes?.map { it.toLocal() }
    )
}
