package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.states

import com.ops.airportr.domain.model.senddevicedata.response.SendDeviceDataResponse
import com.ops.airportr.domain.model.storetrackingdata.StoreTrackingDataResponse

data class StoreTrackingDataState(
    var isLoading: Boolean = false,
    var response: StoreTrackingDataResponse? = null,
    var error: String? = null
)
