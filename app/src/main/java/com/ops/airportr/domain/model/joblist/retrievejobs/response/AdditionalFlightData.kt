package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AdditionalFlightData(
    @SerializedName("additionalFlightDataId")
    var additionalFlightDataId: String? = "",
    @SerializedName("bookingReference")
    var bookingReference: String? = "",
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String? = "",
    @SerializedName("finalAirportCode")
    var finalAirportCode: String? = "",
    @SerializedName("finalAirportName")
    var finalAirportName: String? = "",
    @SerializedName("finalAirportCityName")
    var finalAirportCityName: String? = "",
    @SerializedName("finalAirportCountryName")
    var finalAirportCountryName: String? = "",
    @SerializedName("flightLeg")
    var flightLeg: Int? = 0,
    @SerializedName("dateTimeAddedUTC")
    var dateTimeAddedUTC: Boolean? = false,
    @SerializedName("dateTimeModifiedUTC")
    var dateTimeModifiedUTC: Int? = 0,
) : Parcelable
