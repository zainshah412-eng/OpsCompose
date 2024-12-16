package com.ops.airportr.domain.model.resetpassword


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class ResetPasswordParam(
    @SerializedName("emailAddress")
    var emailAddress: String? = null
) : Parcelable