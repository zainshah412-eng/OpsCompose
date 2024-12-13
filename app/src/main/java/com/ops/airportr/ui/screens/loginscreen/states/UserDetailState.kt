package com.ops.airportr.ui.screens.loginscreen.states

import com.ops.airportr.domain.model.login.AuthTokenResp
import com.ops.airportr.domain.model.user.UserDetails

data class UserDetailState(
    val isLoading: Boolean = false,
    var userDetailResponse: UserDetails? = null,
    var error: String? = null
)