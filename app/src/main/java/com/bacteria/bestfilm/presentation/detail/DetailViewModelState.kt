package com.bacteria.bestfilm.presentation.detail

import com.bacteria.bestfilm.domain.entity.FilmEntity
import com.bacteria.bestfilm.domain.entity.RouteEntity

sealed class DetailViewModelState {
    object Init : DetailViewModelState()

    data class IsLoading(val isLoading: Boolean) : DetailViewModelState()
    data class Success(val result: FilmEntity, val route: RouteEntity) : DetailViewModelState()
    data class Error(val error: String?) : DetailViewModelState()
}
