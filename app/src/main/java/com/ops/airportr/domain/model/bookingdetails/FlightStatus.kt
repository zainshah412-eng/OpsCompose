package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlightStatus(
    @SerializedName("additionalFlightData")
    var additionalFlightData: String? = null,
    @SerializedName("flightDirection")
    var flightDirection: Int = 0,
    @SerializedName("flightInfo")
    var flightInfo: FlightInfo? = FlightInfo(),
    @SerializedName("flightLeg")
    var flightLeg: Int = 0,
    @SerializedName("flightStatusDetails")
    var flightStatusDetails: ArrayList<FlightStatusDetail>? = ArrayList(),
    @SerializedName("manualFlightData")
    var manualFlightData: String? = null,
    @SerializedName("primaryLeg")
    var primaryLeg: Int = 0,
    @SerializedName("secondaryLeg")
    var secondaryLeg: Int = 0,
    @SerializedName("totalNumberOfLegs")
    var totalNumberOfLegs: Int = 0
) : Parcelable