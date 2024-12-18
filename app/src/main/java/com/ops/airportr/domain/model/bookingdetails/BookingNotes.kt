package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingNotes(
    @SerializedName("bookingNoteId")
    var bookingNoteId: String? = "",
    @SerializedName("userId")
    var userId: String? = "",
    @SerializedName("dateTimeCreatedUTC")
    var dateTimeCreatedUTC: String? = "",
    @SerializedName("bookingReference")
    var bookingReference: String? = "",
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String? = "",
    @SerializedName("text")
    var text: String? = "",
    @SerializedName("priority")
    var priority: Int = 0,
    @SerializedName("author")
    var author: String? = "",
    @SerializedName("user")
    var user: UserX = UserX()
) : Parcelable