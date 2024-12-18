package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AirlineCodeShare(
    @SerializedName("airlineCode")
    var airlineCode: String = "",
    @SerializedName("airlineName")
    var airlineName: String = "",
    @SerializedName("codeShareFlightNumber")
    var codeShareFlightNumber: String = ""
) : Parcelable