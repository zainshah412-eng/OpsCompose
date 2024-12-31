package com.ops.airportr.domain.model.whatsnew.updatenew

import com.google.gson.annotations.SerializedName

data class WhatNewResponseItem(
    val date: String,
    @SerializedName("details")
    val featureDetail: String,
    @SerializedName("featureLists")
    val list: List<Message>,
    val version: String
)

data class WhatNewResponseModel(
    val currentVersion: String?,
    val date: String?,
    val detail: String?,
    val featureLists: List<FeatureLists?>?,
    val newVersion: String?
)

data class FeatureLists(
    val date: String?,
    val featureDetail: String?,
    val list: List<String?>?
)

data class UpdateAvailableResponse(
    val date: String,
    val featureDetail: String,
    val list: ArrayList<UpdateAvailableItem>,
    val version: String
)

data class UpdateAvailableItem(
    val featureName: String,
    val version: String?,
    val featureDetail: String,
    val list: List<String?>?,
)

data class GetApiVersionResponseModel(
    @SerializedName("currentVersion")
    val currentVersion: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("detail")
    val detail: String?,
    @SerializedName("featureLists")
    val featureLists: List<FeatureListsModel?>?,
    @SerializedName("newVersion")
    val newVersion: String?
)

data class FeatureListsModel(
    @SerializedName("date")
    val date: String?,
    @SerializedName("featureDetail")
    val featureDetail: String?,
    @SerializedName("list")
    val list: List<String?>?
)




