package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Action(
    @SerializedName("jobLinkedActionId")
    var jobLinkedActionId: String = "",
    @SerializedName("actionType")
    var actionType: Int = 0,
    @SerializedName("isComplete")
    var isComplete: Boolean = false,
    @SerializedName("isMandatory")
    var isMandatory: Boolean = false,
    @SerializedName("dateTimeCompletedUTC")
    var dateTimeCompletedUTC: String? = null,
    @SerializedName("completedByUserId")
    var completedByUserId: String? = null
) : Parcelable
