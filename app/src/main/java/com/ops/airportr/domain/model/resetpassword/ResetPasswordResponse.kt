package com.ops.airportr.domain.model.resetpassword


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ResetPasswordResponse(
    @SerializedName("resetPasswordUrl")
    var resetPasswordUrl: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("responseStatus")
    var responseStatus: Int? = null,
    @SerializedName("validationErrors")
    var validationErrors: List<String>? = null
) : Parcelable