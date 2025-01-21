package com.ops.airportr.domain.model.bookingnotes

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ops.airportr.domain.model.user.User
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingNote(
    @SerializedName("author")
    val author: String,
    @SerializedName("bookingJourneyReference")
    val bookingJourneyReference: String,
    @SerializedName("bookingNoteId")
    val bookingNoteId: String,
    @SerializedName("bookingReference")
    val bookingReference: String,
    @SerializedName("dateTimeCreatedUTC")
    val dateTimeCreatedUTC: String,
    @SerializedName("priority")
    val priority: Int,
    @SerializedName("text")
    val text: String,
    @SerializedName("user")
    val user: UserXX,
    @SerializedName("userId")
    val userId: String
) : Parcelable