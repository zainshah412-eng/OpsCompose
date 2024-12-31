package com.ops.airportr.domain.model.whatsnew

import com.google.gson.annotations.SerializedName

data class WhatsNewResponse(
    @SerializedName("data")
    var `data`: List<Data?>?,
    @SerializedName("meta")
    var meta: Meta?
)