package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.states

import com.ops.airportr.domain.model.applyactionupdate.response.ApplyActionUpdateSealResponse

data class ApplyActionUpdateNewState(
    var isLoading: Boolean = false,
    var response: ApplyActionUpdateSealResponse? = null,
    var error: String? = null
)
