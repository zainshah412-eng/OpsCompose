package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ops.airportr.domain.model.joblist.retrievejobs.response.ArrivalAirport
import com.ops.airportr.domain.model.joblist.retrievejobs.response.ArrivalDateTimeDetails
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlightStatusDetail(
    @SerializedName("airlineCode")
    var airlineCode: String = "",
    @SerializedName("airlineCodeDisplayName")
    var airlineCodeDisplayName: String = "",
    @SerializedName("airlineCodeShare")
    var airlineCodeShare: ArrayList<AirlineCodeShare>? = ArrayList(),
    @SerializedName("airlineName")
    var airlineName: String = "",
    @SerializedName("arrivalAirport")
    var arrivalAirport: ArrivalAirport? = ArrivalAirport(),
    @SerializedName("arrivalDateTimeDetails")
    var arrivalDateTimeDetails: ArrivalDateTimeDetails? = ArrivalDateTimeDetails(),
    @SerializedName("departureAirport")
    var departureAirport: DepartureAirport? = DepartureAirport(),
    @SerializedName("departureDateTimeDetails")
    var departureDateTimeDetails: DepartureDateTimeDetails? = DepartureDateTimeDetails(),
    @SerializedName("flightLeg")
    var flightLeg: Int = 0,
    @SerializedName("flightNumber")
    var flightNumber: String = "",
    @SerializedName("isCodeShare")
    var isCodeShare: Boolean = false,
    @SerializedName("isFinalDestination")
    var isFinalDestination: Boolean = false,
    @SerializedName("isPrimaryLeg")
    var isPrimaryLeg: Boolean = false,
    @SerializedName("isSecondaryLeg")
    var isSecondaryLeg: Boolean = false,
    @SerializedName("isWetLease")
    var isWetLease: Boolean = false,
    @SerializedName("operatingAirlineCode")
    var operatingAirlineCode: String = "",
    @SerializedName("operatingAirlineName")
    var operatingAirlineName: String = "",
    @SerializedName("operatingFlightNumber")
    var operatingFlightNumber: String = "",
    @SerializedName("wetLeaseOperatorCode")
    var wetLeaseOperatorCode: String? = null
) : Parcelable