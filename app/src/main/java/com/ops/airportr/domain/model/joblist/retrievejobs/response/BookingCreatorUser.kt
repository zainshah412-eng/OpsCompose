package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingCreatorUser(
    val contactNumber: String? = "",
    val contactNumberCountryCode: String? = "",
    val currentUserLocation: String? = "",
    val dateTimeImageAddedUTC: String? = "",
    val emailAddress: String? = "",
    val firstName: String? = "",
    val imageId: String? = "",
    val jobTitle: String? = "",
    val lastName: String? = "",
    val mobileNumber: String? = "",
    val mobileNumberCountryCode: String? = "",
    val organisationId: String? = "",
    val title: String? = "",
    val userId: String? = "",
    val userRoles: List<UserRole>? = null
) : Parcelable