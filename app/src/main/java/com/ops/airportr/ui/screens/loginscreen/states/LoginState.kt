package com.ops.airportr.ui.screens.loginscreen.states

import com.ops.airportr.domain.model.login.AuthTokenResp

data class LoginState(
    val isLoading: Boolean = false,
    var loginResponse: AuthTokenResp? = null,
    var error: String? = null
)