package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ops.airportr.domain.model.joblist.retrievejobs.response.GeoCoord
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DeliveryLocation(
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