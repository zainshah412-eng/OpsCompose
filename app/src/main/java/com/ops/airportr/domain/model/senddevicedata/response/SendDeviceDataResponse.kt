package com.ops.airportr.domain.model.senddevicedata.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SendDeviceDataResponse(
    @SerializedName("description")
    var description: String?,
    @SerializedName("responseStatus")
    var responseStatus: String?,
    @SerializedName("validationErrors")
    var validationErrors: List<String?>?
) : Parcelable