package com.bacteria.bestfilm.presentation.model

import com.bacteria.bestfilm.domain.entity.SizeEntity

  class Size(val large: String?, val medium: String?, val small: String?)

fun SizeEntity.toSize(): Size {
    return Size(large, medium, small)
}
