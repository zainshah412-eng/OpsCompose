package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AlarmOverview(
    @SerializedName("unacknowledgedAlarmCount")
    var unacknowledgedAlarmCount: Int? = 0,
    @SerializedName("acknowledgedAlarmCount")
    var acknowledgedAlarmCount: Int? = 0,
    @SerializedName("resolvedAlarmCount")
    var resolvedAlarmCount: Int? = 0
) : Parcelable