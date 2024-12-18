package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddOnProduct(
    @SerializedName("bookingJourneyLinkedAddOnProductId")
    var bookingJourneyLinkedAddOnProductId: String? = "",
    @SerializedName("addOnProductId")
    var addOnProductId: String? = "",
    @SerializedName("addOnProductType")
    var addOnProductType: Int? = 0
) : Parcelable