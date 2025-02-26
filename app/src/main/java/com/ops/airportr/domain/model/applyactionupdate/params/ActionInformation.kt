package com.ops.airportr.domain.model.applyactionupdate.params

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActionInformation(
    @SerializedName("luggageIdentifierValue")
    var luggageIdentifierValue: String? = null,
    @SerializedName("luggageIdentifierType")
    var luggageIdentifierType: Int? = null,
    @SerializedName("actionTypes")
    var actionTypes: Int = 0,
    @SerializedName("actionValues")
    var actionValues: List<String>? = null,
    @SerializedName("luggageCategory")
    var luggageCategory: Int? = null,
    @SerializedName("scanType")
    var scanType: Int? = null,
    @SerializedName("isAmadeusAction")
    var isAmadeusAction: Boolean? = null
) : Parcelable
