package com.bacteria.bestfilm.presentation.main.ui.profile

import androidx.lifecycle.*
import com.bacteria.bestfilm.domain.entity.UserEntity
import com.bacteria.bestfilm.domain.usecase.LoadFilms
import com.bacteria.bestfilm.domain.usecase.LoadProfile
import com.bacteria.bestfilm.presentation.main.ui.poster.PosterViewModelState
import com.bacteria.bestfilm.presentation.model.FilmList
import com.bacteria.bestfilm.presentation.model.User
import com.bacteria.bestfilm.presentation.model.toFilmList
import com.bacteria.bestfilm.presentation.model.toUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val loadProfile: LoadProfile
) : ViewModel() {


    private val _state = MutableStateFlow<ProfileViewModelState>(ProfileViewModelState.Init)
    val state: StateFlow<ProfileViewModelState> get() = _state

    private fun setLoading() {
        _state.value = ProfileViewModelState.IsLoading(true)
    }

    private fun hideLoading() {
        _state.value = ProfileViewModelState.IsLoading(false)
    }

    private fun filmsLoaded(response: User) {
        _state.value = ProfileViewModelState.Success(
            response
        )
    }

    private fun showError(failure: String?) {
        _state.value = ProfileViewModelState.Error(failure)
    }

    init {
        //TODO: add user country code
        viewModelScope.launch(Dispatchers.IO) {
            loadProfile.invoke("MX")
                .onStart {
                    setLoading()
                }
                .catch { ex ->
                    hideLoading()
                    showError(ex.message)
                }
                .collect { result ->
                    hideLoading()
                    filmsLoaded(result.toUser())
                }
        }
    }

}