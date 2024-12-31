package com.ops.airportr.domain.model.whatsnew

import com.google.gson.annotations.SerializedName

data class FeatureLists(
    @SerializedName("date")
    var date: String?,
    @SerializedName("featureDetail")
    var featureDetail: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("list")
    var list: List<String?>?
)