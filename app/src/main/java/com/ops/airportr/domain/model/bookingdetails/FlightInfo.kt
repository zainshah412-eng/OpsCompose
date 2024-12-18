package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlightInfo(
    @SerializedName("dateTimeAddedUTC")
    var dateTimeAddedUTC: String? = "",
    @SerializedName("id")
    var id: String = "",
    @SerializedName("requestFlightNumber")
    var requestFlightNumber: String? = "",
    @SerializedName("requestedDateTime")
    var requestedDateTime: String? = ""
) : Parcelable