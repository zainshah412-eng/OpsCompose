package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExcessBaggagePayment(
    @SerializedName("hasExcessBaggagePayment")
    var hasExcessBaggagePayment: Boolean = false,
    @SerializedName("isExcessBaggagePaymentSettled")
    var isExcessBaggagePaymentSettled: Boolean = false,
    @SerializedName("doesExcessReqManualInput")
    var doesExcessReqManualInput: Boolean = false,
    @SerializedName("excessBags")
    var excessBags: Int = 0,
    @SerializedName("excessBagCharge")
    var excessBagCharge: Double = 0.0,
    @SerializedName("overweightBags")
    var overweightBags: Int = 0,
    @SerializedName("overweightBagCharge")
    var overweightBagCharge: Double = 0.0,
    @SerializedName("totalCharge")
    var totalCharge: Double = 0.0,
    @SerializedName("currencySymbol")
    val currencySymbol: String? = null,
    @SerializedName("note")
    var note: String = "",
    @SerializedName("receiptImageURL")
    var receiptImageURL: String? = null,
    @SerializedName("paymentDateTime")
    var paymentDateTime: String? = null,
    @SerializedName("paymentStatus")
    var paymentStatus: Int = 0,
    @SerializedName("paymentMode")
    var paymentMode: Int = 0
) : Parcelable


@Parcelize
data class ExtraBagPayment(
    @SerializedName("currencySymbol")
    val currencySymbol: String? = null,
    @SerializedName("doesExtraReqManualInput")
    val doesExtraReqManualInput: Boolean? = false,
    @SerializedName("extraBagCharge")
    val extraBagCharge: Int? = 0,
    @SerializedName("extraBags")
    val extraBags: Int? = 0,
    @SerializedName("hasExtraBagPayment")
    val hasExtraBagPayment: Boolean? = false,
    @SerializedName("isExtraBagPaymentSettled")
    val isExtraBagPaymentSettled: Boolean? = false,
    @SerializedName("paymentDateTime")
    val paymentDateTime: String? = null,
    @SerializedName("paymentMode")
    val paymentMode: Int? = 0,
    @SerializedName("paymentStatus")
    val paymentStatus: String? = null,
    @SerializedName("totalCharge")
    val totalCharge: Double? = 0.0
) : Parcelable