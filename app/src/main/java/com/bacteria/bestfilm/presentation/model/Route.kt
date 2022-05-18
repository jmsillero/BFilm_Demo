package com.bacteria.bestfilm.presentation.model

import com.bacteria.bestfilm.domain.entity.RouteEntity

  class Route(
    val code: String?,
    val sizes: Size?
)

fun RouteEntity.toRoute(): Route {
    return Route(code, sizes?.toSize())
}
