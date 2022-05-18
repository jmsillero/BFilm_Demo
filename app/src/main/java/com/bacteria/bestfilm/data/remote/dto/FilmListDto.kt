package com.bacteria.bestfilm.data.remote.dto

import com.bacteria.bestfilm.domain.entity.FilmListEntity
import com.google.gson.annotations.SerializedName

class FilmListDto(
    @SerializedName("movies") val movies: List<FilmDto>,
    val routes: List<RouteDto>?
) {
    fun toEntity(): FilmListEntity {
        return FilmListEntity(movies.map { it.toEntity() }, routes?.map { it.toEntity() })
    }
}