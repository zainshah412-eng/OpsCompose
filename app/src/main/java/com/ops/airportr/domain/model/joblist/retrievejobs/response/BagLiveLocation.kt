package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BagLiveLocation(
    @SerializedName("luggageCode")
    var luggageCode: String? = "",
    @SerializedName("geoLocationMode")
    var geoLocationMode: String? = "",
    @SerializedName("locationId")
    var locationId: String? = "",
    @SerializedName("geoCoords")
    var geoCoords: GeoCoord? = GeoCoord(),
    @SerializedName("currentChampion")
    var currentChampion: String? = "",
) : Parcelable