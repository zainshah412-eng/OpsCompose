package com.ops.airportr.domain.model.joblist.retrievejobs.params

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RetrieveJobsParams(
    @SerializedName("userId")
    var userId: String = "",
    @SerializedName("endDateTimeUTC")
    var endDateTimeUTC: String = "",
    @SerializedName("locationIds")
    var locationIds: ArrayList<String> = ArrayList(),
    @SerializedName("jobType")
    var jobType: ArrayList<Int> = arrayListOf(1),
    @SerializedName("startDateTimeUTC")
    var startDateTimeUTC: String = ""
) : Parcelable
