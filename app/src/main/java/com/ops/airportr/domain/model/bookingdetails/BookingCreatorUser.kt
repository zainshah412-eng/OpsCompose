package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingCreatorUser(
    @SerializedName("userId")
    var userId: String ?= null,
    @SerializedName("organisationId")
    var organisationId: String ?= null,
    @SerializedName("imageId")
    var imageId: String ?= null,
    @SerializedName("dateTimeImageAddedUTC")
    var dateTimeImageAddedUTC: String ?= null,
    @SerializedName("title")
    var title: String ?= null,
    @SerializedName("firstName")
    var firstName: String ?= "",
    @SerializedName("lastName")
    var lastName: String ?= "",
    @SerializedName("jobTitle")
    var jobTitle: String ?= null,
    @SerializedName("currentUserLocation")
    var currentUserLocation: String? = null,
    @SerializedName("contactNumber")
    var contactNumber: String ?= null,
    @SerializedName("contactNumberCountryCode")
    var contactNumberCountryCode: String ?= null,
    @SerializedName("mobileNumber")
    var mobileNumber: String ?= "",
    @SerializedName("mobileNumberCountryCode")
    var mobileNumberCountryCode: String ?= null,
    @SerializedName("emailAddress")
    var emailAddress: String ?= "",
    @SerializedName("userRoles")
    var userRoles: ArrayList<UserRole> = ArrayList()
) : Parcelable