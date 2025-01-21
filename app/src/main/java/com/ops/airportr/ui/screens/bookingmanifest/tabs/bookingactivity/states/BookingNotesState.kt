package com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity.states

import com.ops.airportr.domain.model.bookingnotes.GetBookingNotesByReference

data class BookingNotesState(
    var isLoading: Boolean = false,
    var response: GetBookingNotesByReference? = null,
    var error: String? = null
)