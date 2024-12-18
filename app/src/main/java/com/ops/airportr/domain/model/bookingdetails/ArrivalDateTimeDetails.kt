package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArrivalAirport(
    @SerializedName("airportCityName")
    var airportCityName: String? = "",
    @SerializedName("airportCode")
    var airportCode: String? = "",
    @SerializedName("airportCountryCode")
    var airportCountryCode: String? = "",
    @SerializedName("airportCountryName")
    var airportCountryName: String? = "",
    @SerializedName("airportGeoCoord")
    var airportGeoCoord: AirportGeoCoord? = AirportGeoCoord(),
    @SerializedName("airportName")
    var airportName: String? = "",
    @SerializedName("airportTerminal")
    var airportTerminal: String? = ""
) : Parcelable