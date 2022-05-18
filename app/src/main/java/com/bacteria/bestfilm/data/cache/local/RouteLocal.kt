package com.bacteria.bestfilm.data.cache.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bacteria.bestfilm.domain.entity.RouteEntity
import com.bacteria.bestfilm.presentation.model.Route
import com.bacteria.bestfilm.presentation.model.toSize

@Entity

class RouteLocal(
    @PrimaryKey val code: String,
    val sizes: SizeLocal?
) {
    fun toEntity(): RouteEntity {
        return RouteEntity(code, sizes?.toEntity())
    }
}

fun RouteEntity.toLocal(): RouteLocal {
    return RouteLocal(code!!, sizes?.toLocal())
}

