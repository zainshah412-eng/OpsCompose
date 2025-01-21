package com.ops.airportr.domain.model.getcommunicationlog

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommunicationLog(
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String? = null,
    @SerializedName("bookingReference")
    var bookingReference: String = "",
    @SerializedName("communicationLogId")
    var communicationLogId: String = "",
    @SerializedName("communicationType")
    var communicationType: Int = 0,
    @SerializedName("dateTimeUtcEmailSent")
    var dateTimeUtcEmailSent: String = "",
    @SerializedName("emailAddress")
    var emailAddress: String = "",
    @SerializedName("emailExternalStatus")
    var emailExternalStatus: Int = 0,
    @SerializedName("emailType")
    var emailType: Int = 0,
    @SerializedName("mobileNumber")
    var mobileNumber: String? = null,
    @SerializedName("senderType")
    var senderType: Int = 0,
    @SerializedName("senderUserId")
    var senderUserId: String = "",
    @SerializedName("smsExternalStatus")
    var smsExternalStatus: Int = 0,
    @SerializedName("smsType")
    var smsType: Int = 0
) : Parcelable