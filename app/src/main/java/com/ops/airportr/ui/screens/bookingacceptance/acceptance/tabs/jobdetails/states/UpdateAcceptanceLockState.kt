package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.states

import com.ops.airportr.domain.model.acceptance.response.UpdateAcceptanceLockResponce
import com.ops.airportr.domain.model.login.AuthTokenResp

data class UpdateAcceptanceLockState(
    var isLoading: Boolean = false,
    var response: UpdateAcceptanceLockResponce? = null,
    var error: String? = null
)
