package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class JobRelatedBag(
    @SerializedName("luggageId")
    var luggageId: String? = "",
    @SerializedName("luggageCode")
    var luggageCode: String? = ""
) : Parcelable