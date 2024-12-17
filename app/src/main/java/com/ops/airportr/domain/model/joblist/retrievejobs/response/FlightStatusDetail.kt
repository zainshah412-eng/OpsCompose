package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class FlightStatusDetail(
    @SerializedName("flightNumber")
    var flightNumber: String? = "",
    @SerializedName("airlineCode")
    var airlineCode: String? = "",
    @SerializedName("airlineName")
    var airlineName: String? = "",
    @SerializedName("operatingFlightNumber")
    var operatingFlightNumber: String? = "",
    @SerializedName("operatingAirlineCode")
    var operatingAirlineCode: String? = "",
    @SerializedName("operatingAirlineName")
    var operatingAirlineName: String? = "",
    @SerializedName("airlineCodeDisplayName")
    var airlineCodeDisplayName: String? = "",
    @SerializedName("isCodeShare")
    var isCodeShare: Boolean? = false,
    @SerializedName("isWetLease")
    var isWetLease: Boolean? = false,
    @SerializedName("flightLeg")
    var flightLeg: Int? = 0,
    @SerializedName("isFinalDestination")
    var isFinalDestination: Boolean? = false,
    @SerializedName("departureAirport")
    var departureAirport: DepartureAirport? = DepartureAirport(),
    @SerializedName("departureDateTimeDetails")
    var departureDateTimeDetails: DepartureDateTimeDetails? = DepartureDateTimeDetails(),
    @SerializedName("arrivalAirport")
    var arrivalAirport: ArrivalAirport? = ArrivalAirport(),
    @SerializedName("arrivalDateTimeDetails")
    var arrivalDateTimeDetails: ArrivalDateTimeDetails? = ArrivalDateTimeDetails(),
    @SerializedName("airlineCodeShare")
    var airlineCodeShare: ArrayList<AirlineCodeShare>? = ArrayList(),
    @SerializedName("wetLeaseOperatorCode")
    var wetLeaseOperatorCode: String? = null,
    @SerializedName("isPrimaryLeg")
    var isPrimaryLeg: Boolean? = false,
    @SerializedName("isSecondaryLeg")
    var isSecondaryLeg: Boolean? = false
) : Parcelable