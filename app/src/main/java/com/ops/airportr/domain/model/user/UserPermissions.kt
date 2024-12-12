package com.ops.airportr.domain.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserPermissions(
    @SerializedName("permissionId")
    var permissionId: String? = null,
    @SerializedName("permissionType")
    var permissionType: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("dateTimeAddedUTC")
    var dateTimeAddedUTC: String? = null,
) : Parcelable