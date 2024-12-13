package com.ops.airportr.domain.model.user

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class User(
    @SerializedName("biography")
    var biography: String? = null,
    @SerializedName("cardDetails")
    var cardDetails: CardDetails? = null,
    @SerializedName("contactNumber")
    var contactNumber: String? = "",
    @SerializedName("contactNumberCountryCode")
    var contactNumberCountryCode: String? = null,
    @SerializedName("dateTimeCreatedUTC")
    var dateTimeCreatedUTC: String? = "",
    @SerializedName("dateTimeModifiedUTC")
    var dateTimeModifiedUTC: String? = "",
    @SerializedName("driverNumber")
    var driverNumber: String? = null,
    @SerializedName("eligibleForInvoice")
    var eligibleForInvoice: Boolean = false,
    @SerializedName("emailAddress")
    var emailAddress: String? = "",
    @SerializedName("firstName")
    var firstName: String? = "",
    @SerializedName("hasMarketingEnrolment")
    var hasMarketingEnrolment: Boolean = false,
    @SerializedName("hasMobileNumber")
    var hasMobileNumber: Boolean = false,
    @SerializedName("imageId")
    var imageId: String? = null,
    @SerializedName("incorrectPasswordAttempts")
    var incorrectPasswordAttempts: Int = 0,
    @SerializedName("isEmailVerified")
    var isEmailVerified: Boolean = false,
    @SerializedName("is2FaEnabled")
    var is2FaEnabled: Boolean? = false,
    @SerializedName("jobTitle")
    var jobTitle: String? = null,
    @SerializedName("lastName")
    var lastName: String? = "",
    @SerializedName("mobileNumber")
    var mobileNumber: String? = "",
    @SerializedName("mobileNumberCountryCode")
    var mobileNumberCountryCode: String? = "",
    @SerializedName("operationalCity")
    var operationalCity: Int = 0,
    @SerializedName("organisationId")
    var organisationId: String? = null,
    @SerializedName("passwordLastResetDatetimeUTC")
    var passwordLastResetDatetimeUTC: String? = "",
    @SerializedName("status")
    var status: Int = 0,
    @SerializedName("title")
    var title: String? = "",
    @SerializedName("userActivePermissions")
    var userActivePermissions: UserPermissions? = null,
    @SerializedName("userCommunicationPreferences")
    var userCommunicationPreferences: UserCommunicationPreferences? = null,
    @SerializedName("userCurrentLocation")
    var userCurrentLocation: UserCurrentLocation? = UserCurrentLocation(),
    @SerializedName("userId")
    var userId: String? = "",
    @SerializedName("userLoginStatus")
    var userLoginStatus: Int = 0,
    @SerializedName("userRelatedLocations")
    var userRelatedLocations: UserRelatedLocationsList? = null,
    @SerializedName("userRelatedOrganisations")
    var userRelatedOrganisations: UserRelatedOrganisationsList? = null,
    @SerializedName("userRevokedPermissions")
    var userRevokedPermissions: UserPermissions? = null,
    @SerializedName("userRoles")
    var userRoles: ArrayList<UserRole> = ArrayList(),
    @SerializedName("airports")
    var airports: ArrayList<UserAssignedAirportsListItem> = ArrayList(),
    @SerializedName("isIATAValidationRequired")
    var isIATAValidationRequired: Boolean = false,
) : Parcelable
