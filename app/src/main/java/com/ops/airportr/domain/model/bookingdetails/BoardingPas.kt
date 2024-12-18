package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BoardingPas(
    @SerializedName("passengerBoardingPassId")
    var passengerBoardingPassId: String? = "",
    @SerializedName("dateTimeAddedUTC")
    var dateTimeAddedUTC: String? = "",
    @SerializedName("dateTimeModifiedUTC")
    var dateTimeModifiedUTC: String? = null,
    @SerializedName("passengerId")
    var passengerId: String? = "",
    @SerializedName("userId")
    var userId: String? = "",
    @SerializedName("scanString")
    var scanString: String? = "",
    @SerializedName("formatCode")
    var formatCode: String? = null,
    @SerializedName("numberOfLegs")
    var numberOfLegs: String? = null,
    @SerializedName("electronicTicket")
    var electronicTicket: String? = null,
    @SerializedName("lastName")
    var lastName: String? = null,
    @SerializedName("firstName")
    var firstName: String? = null
) : Parcelable