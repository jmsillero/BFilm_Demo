package com.bacteria.bestfilm.data.cache.local

import com.bacteria.bestfilm.domain.entity.MediaEntity
import com.bacteria.bestfilm.presentation.model.Media

class MediaLocal(
    val type: String?,
    val code: String?,
    val resource: String?
) {
    fun toEntity(): MediaEntity {
        return MediaEntity(type, code, resource)
    }
}

fun MediaEntity.toLocal(): MediaLocal {
    return MediaLocal(type, code, resource)
}

