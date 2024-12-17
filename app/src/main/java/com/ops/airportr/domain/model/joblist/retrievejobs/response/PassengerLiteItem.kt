package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PassengerLiteItem(
    @SerializedName("pasengerId")
    var pasengerId: String? = "",
    @SerializedName("passportNumber")
    var passportNumber: String? = null,
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("firstName")
    var firstName: String? = "",
    @SerializedName("lastName")
    var lastName: String? = "",
    @SerializedName("emailAddress")
    var emailAddress: String? = "",
    @SerializedName("mobileNumberCountryCode")
    var mobileNumberCountryCode: String? = "",
    @SerializedName("mobileNumber")
    var mobileNumber: String? = "",
    @SerializedName("homeNumberCountryCode")
    var homeNumberCountryCode: String? = "",
    @SerializedName("homeNumber")
    var homeNumber: String? = "",
    @SerializedName("isLeadPassenger")
    var isLeadPassenger: Boolean = false,
    @SerializedName("numberOfHandBags")
    var numberOfHandBags: Int = 0,
    @SerializedName("numberOfHoldBags")
    var numberOfHoldBags: Int = 0,
    @SerializedName("numberOfInjectBags")
    var numberOfInjectBags: Int = 0,
    @SerializedName("numberOfRepatBags")
    var numberOfRepatBags: Int = 0,
    @SerializedName("execClubMembershipLevel")
    var execClubMembershipLevel: String? = null,
    @SerializedName("flightClass")
    var flightClass: String? = null,
    @SerializedName("passengerAirlineVerification")
    var passengerAirlineVerification: PassengerAirlineVerification? = null,
) : Parcelable