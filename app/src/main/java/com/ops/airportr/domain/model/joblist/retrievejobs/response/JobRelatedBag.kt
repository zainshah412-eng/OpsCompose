package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class JobRelatedBag(
    @SerializedName("luggageId")
    var luggageId: String = "",
    @SerializedName("luggageCode")
    var luggageCode: String = ""
) : Parcelable