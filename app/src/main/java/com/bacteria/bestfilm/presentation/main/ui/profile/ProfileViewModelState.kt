package com.bacteria.bestfilm.presentation.main.ui.profile

import com.bacteria.bestfilm.presentation.model.User

sealed class ProfileViewModelState {
    object Init : ProfileViewModelState()

    data class IsLoading(val isLoading: Boolean) : ProfileViewModelState()
    data class Success(val userEntity: User) : ProfileViewModelState()
    data class Error(val error: String?) : ProfileViewModelState()
}
