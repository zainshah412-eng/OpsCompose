package com.ops.airportr.ui.screens.resetpassword.states

import com.ops.airportr.domain.model.login.AuthTokenResp
import com.ops.airportr.domain.model.resetpassword.ResetPasswordResponse
import com.ops.airportr.domain.model.user.UserDetails

data class ResetPasswordState(
    var isLoading: Boolean = false,
    var resetPasswordResponse: ResetPasswordResponse? = null,
    var error: String? = null
)