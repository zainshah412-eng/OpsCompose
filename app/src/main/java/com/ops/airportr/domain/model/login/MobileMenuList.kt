package com.ops.airportr.domain.model.login


import com.google.gson.annotations.SerializedName

data class MobileMenuList(
    @SerializedName("ID")
    var iD: String?,
    @SerializedName("MenuLabel")
    var menuLabel: List<MenuLabel?>?,
    @SerializedName("Module_label")
    var moduleLabel: String?
)