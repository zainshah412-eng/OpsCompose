package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PromotionApplied(
    @SerializedName("promotionAmount")
    var promotionAmount: Int = 0,
    @SerializedName("promotionAmountApplied")
    var promotionAmountApplied: Double = 0.0,
    @SerializedName("promotionCode")
    var promotionCode: String? = "",
    @SerializedName("promotionDescription")
    var promotionDescription: String? = "",
    @SerializedName("promotionId")
    var promotionId: String? = "",
    @SerializedName("promotionName")
    var promotionName: String? = "",
    @SerializedName("promotionType")
    var promotionType: Int = 0
) : Parcelable