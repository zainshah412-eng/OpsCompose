package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VirtualBagClassification(
    @SerializedName("type")
    var type: Int? = 0,
    @SerializedName("count")
    var count: Int? = 0
) : Parcelable
