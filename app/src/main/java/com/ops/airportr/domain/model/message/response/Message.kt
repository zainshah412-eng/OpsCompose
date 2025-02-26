package com.ops.airportr.domain.model.message.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Message(
    @SerializedName("body")
    var body: String?,
    @SerializedName("dateSent")
    var dateSent: String?,
    @SerializedName("direction")
    var direction: String?,
    @SerializedName("errorMessage")
    var errorMessage: String?,
    @SerializedName("status")
    var status: String?
) : Parcelable
