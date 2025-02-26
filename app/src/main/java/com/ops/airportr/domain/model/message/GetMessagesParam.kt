package com.ops.airportr.domain.model.message

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetMessagesParam(
    @SerializedName("bookingReference")
    var bookingReference: String?
) : Parcelable
