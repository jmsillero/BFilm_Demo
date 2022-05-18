package com.bacteria.bestfilm.data.remote.dto

import com.bacteria.bestfilm.domain.entity.MediaEntity

class MediaDto(
    val type: String?,
    val code: String?,
    val resource: String?
) {
    fun toEntity(): MediaEntity {
        return MediaEntity(type, code, resource)
    }
}
