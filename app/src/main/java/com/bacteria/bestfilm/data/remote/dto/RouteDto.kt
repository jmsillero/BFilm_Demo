package com.bacteria.bestfilm.data.remote.dto

import com.bacteria.bestfilm.domain.entity.RouteEntity

class RouteDto(
    val code: String?,
    val sizes: SizeDto?
){
      fun toEntity():RouteEntity{
          return RouteEntity(code, sizes?.toEntity())
      }
  }
