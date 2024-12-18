package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CurrentChampion(
    @SerializedName("championId")
    var championId: String? = "",
    @SerializedName("imageId")
    var imageId: String? = null,
    @SerializedName("dateTimeImageAddedUTC")
    var dateTimeImageAddedUTC: String? = null,
    @SerializedName("firstName")
    var firstName: String? = "",
    @SerializedName("lastName")
    var lastName: String? = "",
    @SerializedName("contactNumber")
    var contactNumber: String? = "",
    @SerializedName("luggageCode")
    var luggageCode: ArrayList<String>? = ArrayList(),
) : Parcelable