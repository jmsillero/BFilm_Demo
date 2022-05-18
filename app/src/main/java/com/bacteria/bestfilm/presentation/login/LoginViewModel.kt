package com.bacteria.bestfilm.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bacteria.bestfilm.domain.entity.LoginEntity
import com.bacteria.bestfilm.domain.entity.UserAuthEntity
import com.bacteria.bestfilm.domain.usecase.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val login: Login
) :
    ViewModel() {

    private val _state = MutableStateFlow<LoginViewModelState>(LoginViewModelState.Init)
    val state: StateFlow<LoginViewModelState> get() = _state

    private fun setLoading() {
        _state.value = LoginViewModelState.IsLoading(true)
    }

    private fun hideLoading() {
        _state.value = LoginViewModelState.IsLoading(false)
    }

    private fun userLogged(response: UserAuthEntity) {
        _state.value = LoginViewModelState.Success(response)
    }

    private fun showError(failure: String?) {
        _state.value = LoginViewModelState.Error(failure)
    }

    val email: MutableLiveData<String> = savedStateHandle.getLiveData("email", "pruebas_beto_ia@yahoo.com")
    val password: MutableLiveData<String> = savedStateHandle.getLiveData("password", "Pruebas01")

    fun performLogin() {
        viewModelScope.launch(Dispatchers.IO) {
            login.invoke(LoginEntity(email.value!!, password.value))
                .onStart {
                    setLoading()
                }
                .catch { ex ->
                    hideLoading()
                    showError(ex.message)

                }
                .collect { result ->
                    hideLoading()
                    userLogged(result)
                }
        }
    }

}

