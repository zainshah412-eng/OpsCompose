package com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity.states

import com.ops.airportr.domain.model.updatelogs.GetActionUpdateLogsResponse

data class GetActionUpdateLogsState(
    var isLoading: Boolean = false,
    var response: GetActionUpdateLogsResponse? = null,
    var error: String? = null
)
