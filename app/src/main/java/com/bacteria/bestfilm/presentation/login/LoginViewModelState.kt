package com.bacteria.bestfilm.presentation.login

import com.bacteria.bestfilm.domain.entity.UserAuthEntity

sealed class LoginViewModelState {
    object Init : LoginViewModelState()

    data class IsLoading(val isLoading: Boolean) : LoginViewModelState()
    data class Success(val result: UserAuthEntity) : LoginViewModelState()
    data class Error(val error: String?) : LoginViewModelState()
}
