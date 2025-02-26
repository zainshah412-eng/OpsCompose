package com.ops.airportr.domain.model.note

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingNoteParams(
    @SerializedName("priority")
    var priority: Int = 0,
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String = "",
    @SerializedName("bookingReference")
    var bookingReference: String = "",
    @SerializedName("text")
    var text: String = "",
    @SerializedName("userId")
    var userId: String = ""
) : Parcelable
