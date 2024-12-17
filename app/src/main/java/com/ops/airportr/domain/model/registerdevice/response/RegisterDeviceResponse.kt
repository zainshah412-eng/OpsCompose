package com.ops.airportr.domain.model.registerdevice.response

import com.google.gson.annotations.SerializedName

data class RegisterDeviceResponse(
    @SerializedName("description")
    var description: String?,
    @SerializedName("responseStatus")
    var responseStatus: String?,
    @SerializedName("validationErrors")
    var validationErrors: List<String?>?
)
