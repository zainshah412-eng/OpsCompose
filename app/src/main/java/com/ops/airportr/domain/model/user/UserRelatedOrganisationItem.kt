package com.ops.airportr.domain.model.user

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRelatedOrganisationItem(
    @SerializedName("userRelatedOrganisationId")
    var userRelatedOrganisationId: String? = null,
    @SerializedName("userId")
    var userId: String? = null,
    @SerializedName("organisationId")
    var organisationId: String? = null,
    @SerializedName("dateTimeAddedUtc")
    var dateTimeAddedUtc: String? = null,
) : Parcelable