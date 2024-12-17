package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PassengerAirlineVerification(
    @SerializedName("airlinePassengerDocumentVerified")
    var airlinePassengerDocumentVerified: String? = null,
    @SerializedName("airlinePassengerReservationVerified")
    var airlinePassengerReservationVerified: String? = null,
    @SerializedName("airlinePassengerCheckInVerified")
    var airlinePassengerCheckInVerified: String? = null,
) : Parcelable