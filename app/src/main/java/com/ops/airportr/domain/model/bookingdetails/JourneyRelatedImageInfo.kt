package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JourneyRelatedImageInfo(
    @SerializedName("imageId")
    var imageId: String? = "",
    @SerializedName("imageType")
    var imageType: Int = 0,
    @SerializedName("dateTimeImageAddedUTC")
    var dateTimeImageAddedUTC: String? = ""
) : Parcelable