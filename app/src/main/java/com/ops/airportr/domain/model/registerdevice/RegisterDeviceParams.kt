package com.ops.airportr.domain.model.registerdevice

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RegisterDeviceParams(
    @SerializedName("deviceToken")
    var deviceToken: String?,
    @SerializedName("notificationPlatform")
    var notificationPlatform: String?,
    @SerializedName("userId")
    var userId: String?
) : Parcelable
