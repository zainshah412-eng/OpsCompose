package com.ops.airportr.ui.screens.searchbooking

import com.ops.airportr.domain.model.joblist.retrievejobs.response.RetrieveJobsResponse
import com.ops.airportr.domain.model.searchbooking.BookingDetail

data class SearchBookingState(
    var isLoading: Boolean = false,
    var response: BookingDetail? = null,
    var error: String? = null
)
