package com.ops.airportr.domain.model.updatelogs

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActionUpdate(
    @SerializedName("actionUpdateId")
    var actionUpdateId: String = "",
    @SerializedName("userId")
    var userId: String = "",
    @SerializedName("dateTimeUTC")
    var dateTimeUTC: String = "",
    @SerializedName("bookingReference")
    var bookingReference: String = "",
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String = "",
    @SerializedName("luggageCode")
    var luggageCode: String = "",
    @SerializedName("actionType")
    var actionType: Int = 0,
    @SerializedName("actionValues")
    var actionValues: String? = "",
    @SerializedName("actionValue")
    var actionValue: String? = "",
    @SerializedName("jobNumber")
    var jobNumber: String? = null,
    @SerializedName("locationType")
    var locationType: Int? = null,
    @SerializedName("locationId")
    var locationId: String? = null,
    @SerializedName("geoCoordinates")
    var geoCoordinates: GeoCoordinates? = null,
    @SerializedName("actionImageId")
    var actionImageId: String? = null,
    @SerializedName("scanType")
    var scanType: Int? = null,
    @SerializedName("userName")
    var userNameuserName: String? = null,
    @SerializedName("actionCreatorRole")
    var actionCreatorRole: Int = 0,
) : Parcelable

