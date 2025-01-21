package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.states

import com.ops.airportr.domain.model.acceptance.response.UpdateAcceptanceLockResponce
import com.ops.airportr.domain.model.senddevicedata.response.SendDeviceDataResponse

data class SmsDeviceDataState(
    var isLoading: Boolean = false,
    var response: SendDeviceDataResponse? = null,
    var error: String? = null
)