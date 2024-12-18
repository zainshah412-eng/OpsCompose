package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DepartureAirport(
    @SerializedName("airportCode")
    var airportCode: String? = "",
    @SerializedName("airportName")
    var airportName: String? = "",
    @SerializedName("airportTerminal")
    var airportTerminal: String? = null,
    @SerializedName("airportGeoCoord")
    var airportGeoCoord: AirportGeoCoord? = AirportGeoCoord(),
    @SerializedName("airportCityName")
    var airportCityName: String? = "",
    @SerializedName("airportCountryCode")
    var airportCountryCode: String? = "",
    @SerializedName("airportCountryName")
    var airportCountryName: String? = ""
) : Parcelable