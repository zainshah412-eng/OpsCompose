package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

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
    @SerializedName("isLeadPassenger")
    var isLeadPassenger: Boolean? = false,
    @SerializedName("emailAddress")
    var emailAddress: String? = "",
    @SerializedName("mobileNumberCountryCode")
    var mobileNumberCountryCode: String? = "",
    @SerializedName("mobileNumber")
    var mobileNumber: String? = "",
    @SerializedName("checkedInStatus")
    var checkedInStatus: Int? = -1
) : Parcelable