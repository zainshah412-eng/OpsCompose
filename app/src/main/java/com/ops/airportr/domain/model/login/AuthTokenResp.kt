package com.ops.airportr.domain.model.login

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class AuthTokenResp(
    @SerializedName("access_token")
    var accessToken: String?,
    @SerializedName("expires_in")
    var expiresIn: Int?,
    @SerializedName("token_type")
    var tokenType: String?
) : Parcelable
