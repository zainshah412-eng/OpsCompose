package com.ops.airportr.domain.model.updatejob

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UpdateUserResponse(
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("responseStatus")
    var responseStatus: Int = 0,
    @SerializedName("validationErrors")
    var validationErrors: List<String> = listOf()
) : Parcelable
