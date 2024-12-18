package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ManualFlightModel(
    @SerializedName("manualFlightModelId")
    var manualFlightModelId: String? = "",
    @SerializedName("arrivalCity")
    var arrivalCity: String? = "",
    @SerializedName("airline")
    var airline: String? = "",
    @SerializedName("airportName")
    var airportName: String? = "",
    @SerializedName("airportTerminal")
    var airportTerminal: String? = "",
    @SerializedName("arrivalDateTime")
    var arrivalDateTime: String? = "",
    @SerializedName("hasLandedNow")
    var hasLandedNow: Boolean? = false,
) : Parcelable