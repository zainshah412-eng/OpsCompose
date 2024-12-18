package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ops.airportr.domain.model.joblist.retrievejobs.response.GeoCoord
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BagLiveLocation(
    @SerializedName("luggageCode")
    var luggageCode: String = "",
    @SerializedName("geoLocationMode")
    var geoLocationMode: Int = 0,
    @SerializedName("locationId")
    var locationId: String? = null,
    @SerializedName("geoCoords")
    var geoCoords: GeoCoord? = GeoCoord(),
    @SerializedName("currentChampion")
    var currentChampion: String = ""
) : Parcelable