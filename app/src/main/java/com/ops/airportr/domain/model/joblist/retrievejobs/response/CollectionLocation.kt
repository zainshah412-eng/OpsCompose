package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CollectionLocation(
    @SerializedName("locationId")
    var locationId: String? = "",
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("shortName")
    var shortName: String? = "",
    @SerializedName("terminal")
    var terminal: String? = null,
    @SerializedName("contactNumber")
    var contactNumber: String? = "",
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("addressLine1")
    var addressLine1: String? = "",
    @SerializedName("addressLine2")
    var addressLine2: String? = "",
    @SerializedName("addressPostCode")
    var addressPostCode: String? = "",
    @SerializedName("addressTown")
    var addressTown: String? = "",
    @SerializedName("geoCoord")
    var geoCoord: GeoCoord? = GeoCoord(),
    @SerializedName("locationType")
    var locationType: Int = 0
) : Parcelable