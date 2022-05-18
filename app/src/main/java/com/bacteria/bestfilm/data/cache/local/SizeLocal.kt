package com.bacteria.bestfilm.data.cache.local

import com.bacteria.bestfilm.domain.entity.SizeEntity

class SizeLocal(val large: String?, val medium: String?, val small: String?) {
    fun toEntity(): SizeEntity {
        return SizeEntity(large, medium, small)
    }
}

fun SizeEntity.toLocal(): SizeLocal {
    return SizeLocal(large, medium, small)
}



