package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JourneyLinkedImage(
    @SerializedName("journeyLinkedImagesId")
    var journeyLinkedImagesId: String? = "",
    @SerializedName("signatureImageId")
    var signatureImageId: String? = "",
    @SerializedName("imageReason")
    var imageReason: Int? = 0,
    @SerializedName("imageType")
    var imageType: Int? = 0,
    @SerializedName("dateTimeImageAddedUTC")
    var dateTimeImageAddedUTC: String? = ""
) : Parcelable