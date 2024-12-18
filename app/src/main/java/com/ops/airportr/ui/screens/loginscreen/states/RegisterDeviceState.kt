package com.ops.airportr.ui.screens.loginscreen.states

import com.ops.airportr.domain.model.login.AuthTokenResp
import com.ops.airportr.domain.model.registerdevice.response.RegisterDeviceResponse

class RegisterDeviceState(
    var isLoading: Boolean = false,
    var registerDeviceResponse: RegisterDeviceResponse? = null,
    var error: String? = null
)