package com.ops.airportr.domain.model.login


import com.google.gson.annotations.SerializedName

data class MenuLabel(
    @SerializedName("Enabled")
    var enabled: String?,
    @SerializedName("ID")
    var iD: String?,
    @SerializedName("label")
    var label: String?
)