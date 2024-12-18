package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JourneyLinkedImage(
    @SerializedName("journeyLinkedImagesId")
    var journeyLinkedImagesId: String? = "",
    @SerializedName("signatureImageId")
    var signatureImageId: String? = "",
    @SerializedName("imageReason")
    var imageReason: Int = 0,
    @SerializedName("imageType")
    var imageType: Int = 0,
    @SerializedName("dateTimeImageAddedUTC")
    var dateTimeImageAddedUTC: String? = ""
) : Parcelable