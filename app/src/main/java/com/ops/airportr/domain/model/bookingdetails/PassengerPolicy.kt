package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PassengerPolicy(
    @SerializedName("showPassengerPolicy")
    var showPassengerPolicy: Boolean = false,
    @SerializedName("passengerPolicyTitleCopy")
    var passengerPolicyTitleCopy: String? = "",
    @SerializedName("passengerPolicyCopy")
    var passengerPolicyCopy: String? = ""
) : Parcelable