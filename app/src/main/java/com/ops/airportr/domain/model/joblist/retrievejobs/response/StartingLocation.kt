package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class StartingLocation(
    @SerializedName("locationId")
    var locationId: String = "",
    @SerializedName("dateTimeCreatedUTC")
    var dateTimeCreatedUTC: String = "",
    @SerializedName("dateTimeModifiedUTC")
    var dateTimeModifiedUTC: String = "",
    @SerializedName("type")
    var type: Int = 0,
    @SerializedName("name")
    var name: String = "",
    @SerializedName("shortName")
    var shortName: String = "",
    @SerializedName("terminal")
    var terminal: String? = null,
    @SerializedName("geoCoord")
    var geoCoord: GeoCoord = GeoCoord(),
    @SerializedName("phoneNumber")
    var phoneNumber: String = "",
    @SerializedName("addressLine1")
    var addressLine1: String = "",
    @SerializedName("addressLine2")
    var addressLine2: String = "",
    @SerializedName("addressLine3")
    var addressLine3: String = "",
    @SerializedName("addressTown")
    var addressTown: String = "",
    @SerializedName("addressPostCode")
    var addressPostCode: String = "",
    @SerializedName("addressCounty")
    var addressCounty: String = "",
    @SerializedName("addressCountry")
    var addressCountry: String = "",
    @SerializedName("locationContactEmail")
    var locationContactEmail: String = ""
) : Parcelable