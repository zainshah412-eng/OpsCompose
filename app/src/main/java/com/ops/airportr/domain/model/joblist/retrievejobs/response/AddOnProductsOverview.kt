package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AddOnProductsOverview(
    @SerializedName("bookingJourneyLinkedAddOnProductId")
    var bookingJourneyLinkedAddOnProductId: String? = "",
    @SerializedName("addOnProductId")
    var addOnProductId: String? = "",
    @SerializedName("addOnProductType")
    var addOnProductType: Int? = 0
) : Parcelable