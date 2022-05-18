package com.bacteria.bestfilm.presentation.model

import com.bacteria.bestfilm.domain.entity.FilmEntity
import java.util.*

class Film(
    val id: Long,
    val rating: String?,
    val media: List<Media>?,
    val cinemas: List<Int>?,
    val position: Int = 0,
    val categories: List<String>?,
    val genre: String?,
    val synopsis: String?,
    val length: String?,
    val releaseDate: Date?,
    val name: String?,
    val code: String?,
    val originalName: String?,
    )

fun FilmEntity.toFilm(): Film {
    return Film(
        id, rating, media?.map { it.toMedia() }, cinemas,
        position, categories, genre, synopsis,
        length, releaseDate, name, code, originalName
    )
}


