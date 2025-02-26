package com.ops.airportr.domain.model.applyactionupdate.params

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ActionUpdateBooking(
    @SerializedName("bookingReference")
    var bookingReference: String = "",
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String = "",
    @SerializedName("dateCreatedUTC")
    var dateCreatedUTC: String = "",
    @SerializedName("jobNumber")
    var jobNumber: String = "",
    @SerializedName("locationId")
    var locationId: String = "",
    @SerializedName("scanLocationGeocoordinates")
    var scanLocationGeocoordinates: ScanLocationGeocoordinates = ScanLocationGeocoordinates(),
    @SerializedName("scanDateTimeUTC")
    var scanDateTimeUTC: String = "",
    @SerializedName("name")
    var name: String = "",
    @SerializedName("locationType")
    var locationType: Int = 0,
    @SerializedName("actionInformation")
    var actionInformation: List<ActionInformation> = listOf()
) : Parcelable
