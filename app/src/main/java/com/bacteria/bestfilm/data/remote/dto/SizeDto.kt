package com.bacteria.bestfilm.data.remote.dto

import com.bacteria.bestfilm.domain.entity.SizeEntity

class SizeDto(val large: String?, val medium: String?, val small: String?) {
    fun toEntity(): SizeEntity {
        return SizeEntity(large, medium, small)
    }
}
