package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserX(
    @SerializedName("firstName")
    var firstName: String? = "",
    @SerializedName("lastName")
    var lastName: String? = "",
    @SerializedName("displayName")
    var displayName: String? = "",
    @SerializedName("userRoles")
    var userRoles: ArrayList<UserRole>? = ArrayList()
) : Parcelable