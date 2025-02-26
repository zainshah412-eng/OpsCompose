package com.ops.airportr.domain.model.storetrackingdata

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ops.airportr.domain.model.user.GeoCoord
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoreTrackingDataParams(
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String? = null,
    @SerializedName("bookingReference")
    var bookingReference: String? = null,
    @SerializedName("estimatedTimeOfArrivalSeconds")
    var estimatedTimeOfArrivalSeconds: Int? = null,
    @SerializedName("geoCoord")
    var geoCoord: GeoCoord? = GeoCoord()
) : Parcelable