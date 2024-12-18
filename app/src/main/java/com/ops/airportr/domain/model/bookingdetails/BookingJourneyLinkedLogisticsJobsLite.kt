package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingJourneyLinkedLogisticsJobsLite(
    @SerializedName("bookingJourneyLinkedLogisticJobId")
    var bookingJourneyLinkedLogisticJobId: String? = "",
    @SerializedName("jobLongId")
    var jobLongId: String? = "",
    @SerializedName("jobShortId")
    var jobShortId: String? = "",
    @SerializedName("logisticsProvider")
    var logisticsProvider: Int? = 0,
    @SerializedName("status")
    var status: Int? = 0,
    @SerializedName("totalNumberOfBags")
    var totalNumberOfBags: Int? = 0,
    @SerializedName("logisticsJobNote")
    var logisticsJobNote: String? = "",
    @SerializedName("refusalReason")
    var refusalReason: String? = null
) : Parcelable