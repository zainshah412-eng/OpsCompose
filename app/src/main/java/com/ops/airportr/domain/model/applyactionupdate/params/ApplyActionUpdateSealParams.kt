package com.ops.airportr.domain.model.applyactionupdate.params

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApplyActionUpdateSealParams(
    @SerializedName("bookingReference")
    var bookingReference: String = "",
    @SerializedName("jobNumber")
    var jobNumber: String = "",
    @SerializedName("userId")
    var userId: String = "",
    @SerializedName("locationType")
    var locationType: Int = 0,
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String = "",
    @SerializedName("actionInformation")
    var actionInformation: List<ActionInformation> = listOf(),
    @SerializedName("actionUpdateBooking")
    var actionUpdateBooking: List<ActionUpdateBooking> = listOf(),
    @SerializedName("name")
    var name: String = ""
) : Parcelable
