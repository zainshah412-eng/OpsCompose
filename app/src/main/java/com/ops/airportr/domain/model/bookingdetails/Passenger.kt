package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Passenger(
    @SerializedName("passengerId")
    var passengerId: String? = "",
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("firstName")
    var firstName: String? = "",
    @SerializedName("lastName")
    var lastName: String? = "",
    @SerializedName("passengerLuggage")
    var passengerLuggage: ArrayList<PassengerLuggage>? = ArrayList(),
    @SerializedName("currentChampions")
    var currentChampions: ArrayList<CurrentChampion>? = ArrayList(),
    @SerializedName("isFlightBooker")
    var isFlightBooker: Boolean = false,
    @SerializedName("isLeadPassenger")
    var isLeadPassenger: Boolean = false,
    @SerializedName("airlineReservationNumber")
    var airlineReservationNumber: String? = "",
    @SerializedName("inboundFlightNumber")
    var inboundFlightNumber: String? = null,
    @SerializedName("inboundBoardingPassNumber")
    var inboundBoardingPassNumber: String? = null,
    @SerializedName("outboundFlightNumber")
    var outboundFlightNumber: String? = "",
    @SerializedName("outboundBoardingPassNumber")
    var outboundBoardingPassNumber: String? = null,
    @SerializedName("frequentFlyerNumber")
    var frequentFlyerNumber: String? = null,
    @SerializedName("passengerDocuments")
    var passengerDocuments: ArrayList<PassengerDocument>? = null,
    @SerializedName("emailAddress")
    var emailAddress: String ?= "",
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("contactNumber")
    var contactNumber: String? = null,
    @SerializedName("mobileNumberCountryCode")
    var mobileNumberCountryCode: String? = "",
    @SerializedName("mobileNumber")
    var mobileNumber: String? = "",
    @SerializedName("holdBagAllowance")
    var holdBagAllowance: String? = null,
    @SerializedName("execClubMembershipLevel")
    var execClubMembershipLevel: Int = 0,
    @SerializedName("flightClass")
    var flightClass: String? = null,
    @SerializedName("boardingPass")
    var boardingPass: ArrayList<BoardingPas>? = null,
    @SerializedName("journeyRelatedImageInfo")
    var journeyRelatedImageInfo: ArrayList<JourneyRelatedImageInfo>? = ArrayList(),
    @SerializedName("passengerValidationStatusFlags")
    var passengerValidationStatusFlags: PassengerValidationStatusFlags? = PassengerValidationStatusFlags(),
    @SerializedName("xrayStatus")
    var xrayStatus: Int = 0,
    @SerializedName("findTravellerStatus")
    var findTravellerStatus: Int = 0,
    @SerializedName("dcsId")
    var dcsId: String? = null,
    @SerializedName("dcsLookup")
    var dcsLookup: String? = null,
    @SerializedName("checkedInStatus")
    var checkedInStatus: Int? = -1
) : Parcelable