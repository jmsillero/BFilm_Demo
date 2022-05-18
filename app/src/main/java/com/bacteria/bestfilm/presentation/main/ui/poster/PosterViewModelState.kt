package com.bacteria.bestfilm.presentation.main.ui.poster

import com.bacteria.bestfilm.presentation.model.Film
import com.bacteria.bestfilm.presentation.model.Route

sealed class PosterViewModelState {
    object Init : PosterViewModelState()

    data class IsLoading(val isLoading: Boolean) : PosterViewModelState()
    data class Success(val result: List<Film>, val posterRoute: Route?) : PosterViewModelState()
    data class Error(val error: String?) : PosterViewModelState()
}
