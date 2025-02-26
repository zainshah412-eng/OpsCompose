package com.ops.airportr.domain.model.applyactionupdate.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApplyActionUpdateSealResponse(
    @SerializedName("partialProcessedBookings")
    var partialProcessedBookings: List<PartialProcessedBooking> = listOf(),
    @SerializedName("failedBookings")
    var failedBookings: List<FailedBooking> = listOf(),
    @SerializedName("description")
    var description: String = "",
    @SerializedName("responseStatus")
    var responseStatus: String = "",
    @SerializedName("validationErrors")
    var validationErrors: List<String> = listOf()
) : Parcelable
