package com.bacteria.bestfilm.presentation.main.ui.poster

import androidx.lifecycle.*
import com.bacteria.bestfilm.domain.entity.LoginEntity
import com.bacteria.bestfilm.domain.entity.UserAuthEntity
import com.bacteria.bestfilm.domain.usecase.LoadFilms
import com.bacteria.bestfilm.domain.usecase.Login
import com.bacteria.bestfilm.presentation.login.LoginViewModelState
import com.bacteria.bestfilm.presentation.model.Film
import com.bacteria.bestfilm.presentation.model.FilmList
import com.bacteria.bestfilm.presentation.model.toFilm
import com.bacteria.bestfilm.presentation.model.toFilmList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PosterViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loadFilms: LoadFilms
) : ViewModel() {
    private val _state = MutableStateFlow<PosterViewModelState>(PosterViewModelState.Init)
    val state: StateFlow<PosterViewModelState> get() = _state

    private fun setLoading() {
        _state.value = PosterViewModelState.IsLoading(true)
    }

    private fun hideLoading() {
        _state.value = PosterViewModelState.IsLoading(false)
    }

    private fun filmsLoaded(response: FilmList) {
        _state.value = PosterViewModelState.Success(
            response.movies,
            response.routes?.find { r -> r.code == "poster" })
    }

    private fun showError(failure: String?) {
        _state.value = PosterViewModelState.Error(failure)
    }

    init {
        //TODO: add user country code
        viewModelScope.launch(Dispatchers.IO) {
            loadFilms.invoke("MX", "61")
                .onStart {
                    setLoading()
                }
                .catch { ex ->
                    hideLoading()
                    showError(ex.message)
                }
                .collect { result ->
                    hideLoading()
                    filmsLoaded(result.toFilmList())
                }
        }
    }

}