package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.states

import com.ops.airportr.domain.model.senddevicedata.response.SendDeviceDataResponse
import com.ops.airportr.domain.model.updatejob.UpdateUserResponse

data class UpdateJobState(
    var isLoading: Boolean = false,
    var response: UpdateUserResponse? = null,
    var error: String? = null
)
