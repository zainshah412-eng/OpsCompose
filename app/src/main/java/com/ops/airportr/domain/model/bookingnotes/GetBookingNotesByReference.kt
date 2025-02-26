package com.ops.airportr.domain.model.bookingnotes

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GetBookingNotesByReference(
    @SerializedName("bookingNote")
    val bookingNote: ArrayList<BookingNote>?= ArrayList(),
    @SerializedName("bookingNoteCount")
    val bookingNoteCount: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("responseStatus")
    val responseStatus: Int,
    @SerializedName("validationErrors")
    val validationErrors: ArrayList<String>
): Parcelable