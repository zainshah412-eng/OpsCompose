package com.ops.airportr.domain.model.applyactionupdate.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FailedBooking(
    @SerializedName("bookingReference")
    var bookingReference: String = "",
    @SerializedName("reasons")
    var reasons: List<String> = listOf()
) : Parcelable