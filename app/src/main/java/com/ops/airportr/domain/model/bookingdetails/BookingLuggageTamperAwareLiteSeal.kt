package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingLuggageTamperAwareLiteSeal(
    @SerializedName("bookingLuggageTamperAwareSealId")
    var bookingLuggageTamperAwareSealId: String? = "",
    @SerializedName("dateTimeCreatedUTC")
    var dateTimeCreatedUTC: String? = "",
    @SerializedName("tamperAwareSealStatus")
    var tamperAwareSealStatus: Int = 0,
    @SerializedName("tamperAwareSealCode")
    var tamperAwareSealCode: String? = "",
    @SerializedName("userId")
    var userId: String? = "",
    @SerializedName("passengerLuggageId")
    var passengerLuggageId: String? = ""
) : Parcelable