package com.bacteria.bestfilm.domain.entity

import java.util.*

class FilmEntity(
    val id: Long,
    val rating: String?,
    val media: List<MediaEntity>?,
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
){

}
