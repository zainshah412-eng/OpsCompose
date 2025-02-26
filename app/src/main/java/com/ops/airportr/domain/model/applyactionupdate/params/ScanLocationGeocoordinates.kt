package com.ops.airportr.domain.model.applyactionupdate.params

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ScanLocationGeocoordinates(
    @SerializedName("longitude")
    var longitude: Double = 0.0,
    @SerializedName("latitude")
    var latitude: Double = 0.0
) : Parcelable
