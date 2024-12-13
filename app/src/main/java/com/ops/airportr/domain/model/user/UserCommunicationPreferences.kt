package com.ops.airportr.domain.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserCommunicationPreferences(
    @SerializedName("userId")
    var userId: String? = null,
    @SerializedName("emailCommunicationOperations")
    var emailCommunicationOperations: Boolean? = false,
    @SerializedName("emailCommunicationMarketing")
    var emailCommunicationMarketing: Boolean? = false,
    @SerializedName("smsCommunicationOperations")
    var smsCommunicationOperations: Boolean? = false,
    @SerializedName("smsCommunicationMarketing")
    var smsCommunicationMarketing: Boolean? = false,
) : Parcelable