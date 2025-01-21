package com.ops.airportr.domain.model.bookingnotes

data class GetBookingNotesByReference(
    val bookingNote: ArrayList<BookingNote>,
    val bookingNoteCount: Int,
    val description: String,
    val responseStatus: Int,
    val validationErrors: ArrayList<String>
)