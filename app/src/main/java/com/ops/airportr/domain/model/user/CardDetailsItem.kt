package com.ops.airportr.domain.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CardDetailsItem(
    @SerializedName("expiryMonth")
    var expiryMonth: String? = null,
    @SerializedName("expiryYear")
    var expiryYear: String? = null,
    @SerializedName("holderName")
    var holderName: String? = null,
    @SerializedName("number")
    var number: String? = null,
    @SerializedName("cardType")
    var cardType: String? = null,
    @SerializedName("recurringDetailReference")
    var recurringDetailReference: String? = null,
    @SerializedName("isSavedForUser")
    var isSavedForUser: Boolean? = null,
    @SerializedName("createdDateTime")
    var createdDateTime: String? = null
) : Parcelable