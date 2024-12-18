package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RetrieveJob(
    @SerializedName("jobs")
    var jobs: ArrayList<Job>? = ArrayList(),
    @SerializedName("flightStatusInformation")
    var flightStatusInformation: FlightStatusInformation? = FlightStatusInformation(),
    @SerializedName("bookingInformation")
    var bookingInformation: BookingInformation? = BookingInformation(),
) : Parcelable