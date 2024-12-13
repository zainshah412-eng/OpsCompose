package com.ops.airportr.domain.model.user

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class UserAssignedAirportsListItem(
    @SerializedName("airlines")
    var airlines: List<Airline?>?,
    @SerializedName("airportCode")
    var airportCode: String?,
    @SerializedName("airportName")
    var airportName: String?
) : Parcelable