package com.ops.airportr.domain.model.bookingnotes

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserXX(
    @SerializedName("displayName")
    val displayName: String? = "",
    @SerializedName("firstName")
    val firstName: String? = "",
    @SerializedName("lastName")
    val lastName: String? = "",
    @SerializedName("userRoles")
    val userRoles: List<UserRoleX>
): Parcelable