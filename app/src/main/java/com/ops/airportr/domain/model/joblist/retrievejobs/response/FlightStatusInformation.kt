package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlightStatusInformation(
    @SerializedName("flightInfo")
    var flightInfo: FlightInfo? = FlightInfo(),
    @SerializedName("flightStatusDetails")
    var flightStatusDetails: ArrayList<FlightStatusDetail> = ArrayList(),
    @SerializedName("additionalFlightData")
    var additionalFlightData: AdditionalFlightData? = AdditionalFlightData(),
    @SerializedName("flightLeg")
    var flightLeg: Int? = 0,
    @SerializedName("manualFlightData")
    var manualFlightData: ManualFlightModel? = ManualFlightModel(),
    @SerializedName("totalNumberOfLegs")
    var totalNumberOfLegs: Int? = 0,
    @SerializedName("primaryLeg")
    var primaryLeg: Int? = 0,
    @SerializedName("secondaryLeg")
    var secondaryLeg: Int? = 0,
    @SerializedName("flightDirection")
    var flightDirection: Int? = 0
) : Parcelable