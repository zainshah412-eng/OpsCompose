package com.ops.airportr.domain.model.whatsnew

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("createdAt")
    var createdAt: String?,
    @SerializedName("currentVersion")
    var currentVersion: String?,
    @SerializedName("date")
    var date: String?,
    @SerializedName("detail")
    var detail: String?,
    @SerializedName("featureLists")
    var featureLists: List<FeatureLists?>?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("newVersion")
    var newVersion: String?,
    @SerializedName("publishedAt")
    var publishedAt: String?,
    @SerializedName("updatedAt")
    var updatedAt: String?
)