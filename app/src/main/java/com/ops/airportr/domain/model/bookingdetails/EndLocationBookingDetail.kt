package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ops.airportr.domain.model.joblist.retrievejobs.response.GeoCoord
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EndLocationBookingDetail(
    @SerializedName("locationId")
    var locationId: String? = "",
    @SerializedName("dateTimeCreatedUTC")
    var dateTimeCreatedUTC: String? = "",
    @SerializedName("dateTimeModifiedUTC")
    var dateTimeModifiedUTC: String? = "",
    @SerializedName("type")
    var type: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("shortName")
    var shortName: String? = "",
    @SerializedName("terminal")
    var terminal: String? = null,
    @SerializedName("geoCoord")
    var geoCoord: GeoCoord = GeoCoord()
) : Parcelable