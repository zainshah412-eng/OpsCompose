package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobsms.state

import com.ops.airportr.domain.model.message.response.GetMessagesResponse
import com.ops.airportr.domain.model.note.BookingNoteResponseModel

data class JobMessagesState(
    var isLoading: Boolean = false,
    var response: GetMessagesResponse? = null,
    var error: String? = null
)
