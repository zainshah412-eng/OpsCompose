package com.ops.airportr.domain.model.searchbooking

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.ops.airportr.domain.model.bookingdetails.BookingDetails
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingDetail(
    @SerializedName("bookingDetails")
    var bookingDetails: BookingDetails = BookingDetails(),
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("responseStatus")
    var responseStatus: Int = 0,
    @SerializedName("validationErrors")
    var validationErrors: ArrayList<String> = ArrayList()
) : Parcelable
