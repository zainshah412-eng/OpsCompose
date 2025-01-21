package com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity.states

import com.ops.airportr.domain.model.getcommunicationlog.CCDGetCommunicationLogResponse

data class GetCommunicationLogState(
    var isLoading: Boolean = false,
    var response: CCDGetCommunicationLogResponse? = null,
    var error: String? = null
)