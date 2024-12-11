package com.ops.airportr.ui.screens.loginscreen

import com.ops.airportr.domain.model.login.SignInResponse

data class LoginState(
    val isLoading: Boolean = false,
    var loginResponse: SignInResponse? = null,
    val error: String? = null
)