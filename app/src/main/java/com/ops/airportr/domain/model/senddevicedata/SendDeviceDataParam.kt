package com.ops.airportr.domain.model.senddevicedata

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SendDeviceDataParam(
    @SerializedName("appVersion")
    var appVersion: String?,
    @SerializedName("bookingReference")
    var bookingReference: String?,
    @SerializedName("dateTime")
    var dateTime: String?,
    @SerializedName("deviceSoftwareVersion")
    var deviceSoftwareVersion: String?,
    @SerializedName("deviceType")
    var deviceType: String?,
    @SerializedName("internetType")
    var internetType: String?,
    @SerializedName("jobType")
    var jobType: String?
) : Parcelable