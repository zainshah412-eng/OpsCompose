package com.ops.airportr.domain.model.bookingnotes

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRoleX(
    @SerializedName("description")
    val description: String,
    @SerializedName("roleId")
    val roleId: String,
    @SerializedName("roleType")
    val roleType: Int
): Parcelable