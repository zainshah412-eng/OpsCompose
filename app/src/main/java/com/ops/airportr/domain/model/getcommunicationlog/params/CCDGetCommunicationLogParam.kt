package com.ops.airportr.domain.model.getcommunicationlog.params

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CCDGetCommunicationLogParam(
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String = "",
    @SerializedName("bookingReference")
    var bookingReference: String = ""
) : Parcelable