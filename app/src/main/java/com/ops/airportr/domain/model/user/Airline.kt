package com.ops.airportr.domain.model.user

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Airline(
    @SerializedName("airlineCode")
    var airlineCode: String?,
    @SerializedName("airlineName")
    var airlineName: String?
) : Parcelable