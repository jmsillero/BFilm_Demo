package com.bacteria.bestfilm.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bacteria.bestfilm.domain.entity.*
import com.bacteria.bestfilm.domain.usecase.LoadFilmById
import com.bacteria.bestfilm.domain.usecase.LoadRouteByCode
import com.bacteria.bestfilm.presentation.login.LoginViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    val loadRouteByCode: LoadRouteByCode,
    val loadFilmById: LoadFilmById
) : ViewModel() {


    private val _state = MutableStateFlow<DetailViewModelState>(DetailViewModelState.Init)
    val state: StateFlow<DetailViewModelState> get() = _state

    private fun setLoading() {
        _state.value = DetailViewModelState.IsLoading(true)
    }

    private fun hideLoading() {
        _state.value = DetailViewModelState.IsLoading(false)
    }

    private fun userLogged(response: FilmEntity, route: RouteEntity) {
        _state.value = DetailViewModelState.Success(response, route)
    }

    private fun showError(failure: String?) {
        _state.value = DetailViewModelState.Error(failure)
    }

    fun loadFilm(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            loadFilmById.invoke(id)
                .onStart {
                    setLoading()
                }
                .catch { ex ->
                    hideLoading()
                    showError(ex.message)
                }.combine(loadRouteByCode.invoke("trailer_mp4")) { t1, t2 ->
                    FilmListEntity(
                        listOf(t1),
                        listOf(t2)
                    )
                }
                .collect { result ->
                    hideLoading()
                    userLogged(result.movies[0], result.routes!![0])
                }
        }
    }
}