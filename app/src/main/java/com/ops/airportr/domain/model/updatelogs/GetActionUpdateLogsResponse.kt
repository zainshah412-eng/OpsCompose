package com.ops.airportr.domain.model.updatelogs

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetActionUpdateLogsResponse(
    @SerializedName("actionUpdates")
    var actionUpdates: List<ActionUpdate> = listOf(),
    @SerializedName("bookingActionUpdates")
    var bookingActionUpdates: List<ActionUpdate> = listOf(),
    @SerializedName("countOfEntries")
    var countOfEntries: Int = 0,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("responseStatus")
    var responseStatus: Int = 0,
    @SerializedName("validationErrors")
    var validationErrors: List<String> = listOf()
) : Parcelable
