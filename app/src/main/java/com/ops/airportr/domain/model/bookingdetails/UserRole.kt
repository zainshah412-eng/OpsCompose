package com.ops.airportr.domain.model.bookingdetails

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class UserRole(
    @SerializedName("description")
    var description: String ?= "",
    @SerializedName("roleId")
    var roleId: String ?= "",
    @SerializedName("roleType")
    var roleType: Int = 0
) : Parcelable