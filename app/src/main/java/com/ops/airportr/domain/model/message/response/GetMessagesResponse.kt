package com.ops.airportr.domain.model.message.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetMessagesResponse(
    @SerializedName("bookingReference")
    var bookingReference: String?,
    @SerializedName("messages")
    var messages: ArrayList<Message>
) : Parcelable
