package com.bacteria.bestfilm.presentation.model

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
    val release_date: Date?,
    val name: String?,
    val code: String?,
    val original_name: String?,
    val routes: List<Route>?

) {
    fun getMediaByCode(code: String): String {
        val resource = media?.find { m ->
            m.code == code
        }?.resource

        val path = routes?.find { r -> r.code == code }?.sizes?.medium
        return "$path$resource"
    }
}
