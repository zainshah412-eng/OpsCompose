package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlightInfo(
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("requestFlightNumber")
    var requestFlightNumber: String? = "",
    @SerializedName("requestedDateTime")
    var requestedDateTime: String? = "",
    @SerializedName("dateTimeAddedUTC")
    var dateTimeAddedUTC: String? = ""
) : Parcelable