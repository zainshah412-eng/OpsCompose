package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingDetails(
    @SerializedName("bookingReference")
    var bookingReference: String? = "",
    @SerializedName("bookingStatus")
    var bookingStatus: Int = 0,
    @SerializedName("dateTimeBookingCreatedUTC")
    var dateTimeBookingCreatedUTC: String? = "",
    @SerializedName("dateTimeRevisionCreatedUTC")
    var dateTimeRevisionCreatedUTC: String? = "",
    @SerializedName("dateTimeBookingCancelledUTC")
    var dateTimeBookingCancelledUTC: String? = null,
    @SerializedName("bookingCreatorOrganisation")
    var bookingCreatorOrganisation: String? = null,
    @SerializedName("bookingCreatorUser")
    var bookingCreatorUser: BookingCreatorUser = BookingCreatorUser(),
    @SerializedName("revisionCreatorOrganisation")
    var revisionCreatorOrganisation: String? = null,
    @SerializedName("revisionCreatorUser")
    var revisionCreatorUser: RevisionCreatorUser = RevisionCreatorUser(),
    @SerializedName("bookingJourneyDetails")
    var bookingJourneyDetails: ArrayList<BookingJourneyDetail> = ArrayList(),
    @SerializedName("dateTimeBookingCreatedLocalTime")
    var dateTimeBookingCreatedLocalTime: String? = "",
    @SerializedName("dateTimeRevisionCreatedLocalTime")
    var dateTimeRevisionCreatedLocalTime: String? = "",
    @SerializedName("dateTimeBookingCancelledLocalTime")
    var dateTimeBookingCancelledLocalTime: String? = null
) : Parcelable