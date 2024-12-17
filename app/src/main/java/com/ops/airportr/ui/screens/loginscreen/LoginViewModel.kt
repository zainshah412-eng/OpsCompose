package com.ops.airportr.ui.screens.loginscreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.joblist.retrievejobs.params.RetrieveJobsParams
import com.ops.airportr.domain.model.registerdevice.RegisterDeviceParams
import com.ops.airportr.domain.use_case.RegisterDeviceUseCase
import com.ops.airportr.domain.use_case.login.GetAuthUseCase
import com.ops.airportr.domain.use_case.login.GetUserDetailUseCase
import com.ops.airportr.ui.screens.loginscreen.states.LoginState
import com.ops.airportr.ui.screens.loginscreen.states.RegisterDeviceState
import com.ops.airportr.ui.screens.loginscreen.states.UserDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getAuthUseCase: GetAuthUseCase,
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val registerDeviceUseCase: RegisterDeviceUseCase
) : ViewModel() {
    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state
    private val _stateUserDetail = mutableStateOf(UserDetailState())
    val stateUserDetail: State<UserDetailState> = _stateUserDetail
    private val _stateRegisterDevice = mutableStateOf(RegisterDeviceState())
    val stateRegisterDevice: State<RegisterDeviceState> = _stateRegisterDevice

    fun authToken(
        url: String, grant_type: String,
        username: String, password: String,
        clientId: String?, role: String?,
        appVersion: String?, deviceModel: String?,
        deviceUUID: String?, deviceSystemName: String?,
        deviceSystemVersion: String?, networkType: String?,
        networkSpeed: String?, permissionsCamera: String?,
        permissionsLocation: String?
    ) {
        getAuthUseCase(
            url,
            grant_type,
            username,
            password,
            clientId,
            role,
            appVersion,
            deviceModel,
            deviceUUID,
            deviceSystemName,
            deviceSystemVersion,
            networkType,
            networkSpeed,
            permissionsCamera,
            permissionsLocation
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _state.value = LoginState(loginResponse = result.data)
                        }

                        is Either.Error -> {
                            _state.value = LoginState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }
                is Resource.Error -> {
                    _state.value = LoginState(error = it.message)
                }

                is Resource.Loading -> {
                    _state.value = LoginState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }


    fun getUserDetail(url: String){
        getUserDetailUseCase(
            url
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _stateUserDetail.value = UserDetailState(userDetailResponse = result.data)
                        }

                        is Either.Error -> {
                            _stateUserDetail.value = UserDetailState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }

                is Resource.Error -> {
                    _stateUserDetail.value = UserDetailState(error = it.message)
                }

                is Resource.Loading -> {
                    _stateUserDetail.value = UserDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun registerDevice(url: String, params: RegisterDeviceParams){
        registerDeviceUseCase(
            url,
            params
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _stateRegisterDevice.value = RegisterDeviceState(registerDeviceResponse = result.data)
                        }

                        is Either.Error -> {
                            _stateRegisterDevice.value = RegisterDeviceState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }

                is Resource.Error -> {
                    _stateRegisterDevice.value = RegisterDeviceState(error = it.message)
                }

                is Resource.Loading -> {
                    _stateRegisterDevice.value = RegisterDeviceState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
    fun resetAuthError() {
        _state.value = _state.value.copy(error = null)
    }

    fun resetUserDetailError() {
        _stateUserDetail.value = _stateUserDetail.value.copy(error = null)
    }



}