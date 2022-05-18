package com.bacteria.bestfilm.presentation.model

import com.bacteria.bestfilm.domain.entity.MediaEntity

  class Media(
    val type: String?,
    val code: String?,
    val resource: String?
)

fun MediaEntity.toMedia(): Media {
    return Media(type, code, resource)
}
