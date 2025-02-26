package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobnotes.state

import com.ops.airportr.domain.model.note.BookingNoteResponseModel
import com.ops.airportr.domain.model.senddevicedata.response.SendDeviceDataResponse

data class AddNoteState(
    var isLoading: Boolean = false,
    var response: BookingNoteResponseModel? = null,
    var error: String? = null
)
