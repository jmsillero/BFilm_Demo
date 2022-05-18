package com.bacteria.bestfilm.data.cache.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bacteria.bestfilm.domain.entity.FilmEntity
import com.bacteria.bestfilm.presentation.model.Film
import com.bacteria.bestfilm.presentation.model.toMedia
import com.google.gson.annotations.SerializedName
import java.util.*


@Entity
class FilmLocal(
    @PrimaryKey val id: Long,
    val rating: String?,
    val media: List<MediaLocal>?,
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


    ) {
    fun toEntity(): FilmEntity {
        return FilmEntity(
            id,
            rating,
            media?.map { it.toEntity() },
            cinemas,
            position,
            categories,
            genre,
            synopsis,
            length,
            releaseDate,
            name,
            code,
            originalName
        )

    }
}

fun FilmEntity.toLocal(): FilmLocal {
    return FilmLocal(
        id, rating, media?.map { it.toLocal() }, cinemas,
        position, categories, genre, synopsis,
        length, releaseDate, name, code, originalName
    )
}

