package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingContact(
    @SerializedName("countryCode")
    var countryCode: String? = "",
    @SerializedName("emailAddress")
    var emailAddress: String? = "",
    @SerializedName("firstName")
    var firstName: String? = "",
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("lastName")
    var lastName: String? = "",
    @SerializedName("phoneNumber")
    var phoneNumber: String? = ""
) : Parcelable