package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DepartureDateTimeDetails(
    @SerializedName("scheduledDate")
    var scheduledDate: String? = "",
    @SerializedName("scheduledTimeUTC")
    var scheduledTimeUTC: String? = "",
    @SerializedName("scheduledTimeLocal")
    var scheduledTimeLocal: String? = "",
    @SerializedName("scheduledDateTimeUTC")
    var scheduledDateTimeUTC: String? = "",
    @SerializedName("dayBeforeScheduledDateTimeUTC")
    var dayBeforeScheduledDateTimeUTC: String? = "",
    @SerializedName("scheduledDateTimeLocal")
    var scheduledDateTimeLocal: String? = "",
    @SerializedName("dayBeforeScheduledDateTimeLocal")
    var dayBeforeScheduledDateTimeLocal: String? = ""
) : Parcelable