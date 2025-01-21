package com.ops.airportr.domain.model.acceptance.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpdateAcceptanceLockResponce(
    @SerializedName("acceptanceLockData")
    var acceptanceLockData: AcceptanceLockData = AcceptanceLockData(),
    @SerializedName("validationErrorMessages")
    var validationErrorMessages: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("responseStatus")
    var responseStatus: Int = 0,
    @SerializedName("validationErrors")
    var validationErrors: List<String> = listOf()
) : Parcelable