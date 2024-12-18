package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DepartureDateTimeDetails(
    @SerializedName("dayBeforeScheduledDateTimeLocal")
    var dayBeforeScheduledDateTimeLocal: String = "",
    @SerializedName("dayBeforeScheduledDateTimeUTC")
    var dayBeforeScheduledDateTimeUTC: String = "",
    @SerializedName("scheduledDate")
    var scheduledDate: String = "",
    @SerializedName("scheduledDateTimeLocal")
    var scheduledDateTimeLocal: String = "",
    @SerializedName("scheduledDateTimeUTC")
    var scheduledDateTimeUTC: String = "",
    @SerializedName("scheduledTimeLocal")
    var scheduledTimeLocal: String = "",
    @SerializedName("scheduledTimeUTC")
    var scheduledTimeUTC: String = ""
) : Parcelable