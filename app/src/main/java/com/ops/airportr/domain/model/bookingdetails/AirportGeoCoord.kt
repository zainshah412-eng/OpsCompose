package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class AirportGeoCoord(
    @SerializedName("latitude")
    var latitude: Double = 0.0,
    @SerializedName("longitude")
    var longitude: Double = 0.0
) : Parcelable