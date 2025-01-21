package com.ops.airportr.domain.model.acceptance.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AcceptanceLockData(
    @SerializedName("customerMessage")
    var customerMessage: String = "",
    @SerializedName("appLockJobStatus")
    var appLockJobStatus: Int = 0,
    @SerializedName("jobStatusMessage")
    var jobStatusMessage: String = "",
    @SerializedName("approvalStatus")
    var approvalStatus: Int = 0,
    @SerializedName("approvalStatusMessage")
    var approvalStatusMessage: String = "",
    @SerializedName("detailedMessage")
    var detailedMessage: String = "",
    @SerializedName("poolTime")
    var poolTime: Int = 0,
    @SerializedName("contactName")
    var contactName: String = ""
) : Parcelable