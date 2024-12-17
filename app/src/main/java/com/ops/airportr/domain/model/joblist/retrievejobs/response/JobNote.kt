package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobNote(
    @SerializedName("jobNoteId")
    var jobNoteId: String = "",
    @SerializedName("userId")
    var userId: String = "",
    @SerializedName("dateTimeCreatedUTC")
    var dateTimeCreatedUTC: String = "",
    @SerializedName("bookingReference")
    var bookingReference: String = "",
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String = "",
    @SerializedName("text")
    var text: String = "",
    @SerializedName("priority")
    var priority: Int = 0
) : Parcelable