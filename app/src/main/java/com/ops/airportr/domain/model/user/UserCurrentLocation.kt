package com.ops.airportr.domain.model.user

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class UserCurrentLocation(
    @SerializedName("dateTimeUTC")
    var dateTimeUTC: String = "",
    @SerializedName("geoCoord")
    var geoCoord: GeoCoord = GeoCoord()
) : Parcelable