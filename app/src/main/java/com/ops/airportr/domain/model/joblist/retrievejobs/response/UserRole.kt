package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRole(
    val description: String? = "",
    val roleId: String? = "",
    val roleType: Int? = -1
) : Parcelable