package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AirlineCodeShare(
    @SerializedName("airlineCode")
    var airlineCode: String? = "",
    @SerializedName("airlineName")
    var airlineName: String? = "",
    @SerializedName("codeShareFlightNumber")
    var codeShareFlightNumber: String? = ""
) : Parcelable