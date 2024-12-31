package com.ops.airportr.domain.model.whatsnew

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("pagination")
    var pagination: Pagination?
)