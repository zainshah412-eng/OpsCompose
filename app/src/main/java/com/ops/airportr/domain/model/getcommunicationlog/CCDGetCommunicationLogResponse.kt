package com.ops.airportr.domain.model.getcommunicationlog

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CCDGetCommunicationLogResponse(
    @SerializedName("communicationLog")
    var communicationLog: List<CommunicationLog> = listOf(),
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("responseStatus")
    var responseStatus: Int = 0,
    @SerializedName("sentEmailCount")
    var sentEmailCount: Int = 0,
    @SerializedName("validationErrors")
    var validationErrors: List<String> = listOf()
) : Parcelable