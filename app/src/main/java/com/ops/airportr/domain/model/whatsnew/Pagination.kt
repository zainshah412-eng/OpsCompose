package com.ops.airportr.domain.model.whatsnew

import com.google.gson.annotations.SerializedName

data class Pagination(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("pageCount")
    var pageCount: Int?,
    @SerializedName("pageSize")
    var pageSize: Int?,
    @SerializedName("total")
    var total: Int?
)