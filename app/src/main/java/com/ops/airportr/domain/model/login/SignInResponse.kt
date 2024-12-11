package com.ops.airportr.domain.model.login


import com.google.gson.annotations.SerializedName

data class SignInResponse(
    @SerializedName("MobileMenuList")
    var mobileMenuList: List<MobileMenuList?>?,
    @SerializedName("owner_site_desc")
    var ownerSiteDesc: String?,
    @SerializedName("owner_site_id")
    var ownerSiteId: String?,
    @SerializedName("Stamp")
    var stamp: String?,
    @SerializedName("status")
    var status: Boolean?,
    @SerializedName("tag")
    var tag: String?,
    @SerializedName("userID")
    var userID: String?,
    @SerializedName("userName")
    var userName: String?,
    @SerializedName("UserPicPath")
    var userPicPath: String?,
    @SerializedName("usertype")
    var usertype: String?,
    @SerializedName("msg")
    var msg: String?
)