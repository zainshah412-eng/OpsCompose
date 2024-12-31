package com.ops.airportr.ui.screens.resetpassword

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.use_case.resetpassword.ResetPasswordUseCase
import com.ops.airportr.ui.screens.resetpassword.states.ResetPasswordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val resetPasswordUseCase: ResetPasswordUseCase
) : ViewModel() {
    private val _state = mutableStateOf(ResetPasswordState())
    val state: State<ResetPasswordState> = _state

    fun resetPassword(
        url: String, emailAddress: String,
    ) {
        resetPasswordUseCase(
            url,
            emailAddress
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _state.value = ResetPasswordState(resetPasswordResponse = result.data)
                        }

                        is Either.Error -> {
                            _state.value = ResetPasswordState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }
                is Resource.Error -> {
                    _state.value = ResetPasswordState(error = it.message)
                }

                is Resource.Loading -> {
                    _state.value = ResetPasswordState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun resetPasswordError() {
        _state.value = _state.value.copy(error = null)
    }



}