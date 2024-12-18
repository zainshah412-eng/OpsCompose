package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PaymentDetails(
    @SerializedName("totalPrice")
    var totalPrice: Double = 0.0,
    @SerializedName("actualBookingPrice")
    var actualBookingPrice: Double = 0.0,
    @SerializedName("currencySymbol")
    var currencySymbol: String? = "",
    @SerializedName("paymentMethod")
    var paymentMethod: String? = "",
    @SerializedName("obscuredCardNumber")
    var obscuredCardNumber: String? = "",
    @SerializedName("paymentDateTimeUTC")
    var paymentDateTimeUTC: String? = "",
    @SerializedName("promotionsApplied")
    var promotionsApplied: ArrayList<PromotionApplied>? = ArrayList(),
) : Parcelable