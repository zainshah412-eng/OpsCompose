package com.ops.airportr.domain.model.updatelogs.params

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetActionUpdateLogsParams(
    @SerializedName("bookingReference")
    var bookingReference: String = "",
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String = ""
) : Parcelable
