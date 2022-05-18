package com.bacteria.bestfilm.data.remote.dto

import com.bacteria.bestfilm.domain.entity.FilmEntity
import com.google.gson.annotations.SerializedName
import java.util.*

class FilmDto(
    val id: Long,
    val rating: String?,
    val media: List<MediaDto>?,
    val cinemas: List<Int>?,
    val position: Int = 0,
    val categories: List<String>?,
    val genre: String?,
    val synopsis: String?,
    val length: String?,
    @SerializedName("release_date") val releaseDate: Date?,
    val name: String?,
    val code: String?,
    @SerializedName("original_name") val originalName: String?,


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
