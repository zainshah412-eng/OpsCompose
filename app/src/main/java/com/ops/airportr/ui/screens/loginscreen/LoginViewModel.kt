package com.ops.airportr.ui.screens.loginscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ops.airportr.common.Resource
import com.ops.airportr.domain.use_case.login.GetLoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase
) : ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    init {
        // getLogin()
    }

    fun getLogin(url:String) {
        getLoginUseCase(url).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = LoginState(loginResponse = it.data)
                }
                is Resource.Error -> {
                    _state.value = LoginState(
                        error = it.message ?: "An unexpected error occured"
                    )
                }
                is Resource.Loading -> {
                    _state.value = LoginState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun resetError() {
        _state.value = _state.value.copy(error = null)
    }

}