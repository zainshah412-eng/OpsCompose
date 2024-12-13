package com.ops.airportr.domain.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRelatedLocationItem(
    @SerializedName("userRelatedLocationsId")
    var userRelatedLocationsId: String? = null,
    @SerializedName("userId")
    var userId: String? = null,
    @SerializedName("locationId")
    var locationId: String? = null,
    @SerializedName("dateTimeAddedUtc")
    var dateTimeAddedUtc: String? = null,
) : Parcelable