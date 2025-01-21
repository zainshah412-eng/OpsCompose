package com.ops.airportr.domain.model.acceptance

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UpdateAcceptanceParam(
    @SerializedName("bookingReference")
    var bookingReference: String = ""
) : Parcelable