package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PassengerDocument(
    @SerializedName("passengerDocumentId")
    var passengerDocumentId: String? = "",
    @SerializedName("passengerReference")
    var passengerReference: String? = "",
    @SerializedName("number")
    var number: String? = "",
    @SerializedName("idType")
    var idType: Int = 0,
    @SerializedName("isPrimaryId")
    var isPrimaryId: Boolean = false,
    @SerializedName("mrz")
    var mrz: String? = "",
    @SerializedName("code")
    var code: String? = "",
    @SerializedName("passengerDocumentPhotoImageId")
    var passengerDocumentPhotoImageId: String? = null,
    @SerializedName("dateTimePassengerDocumentPhotoAddedUTC")
    var dateTimePassengerDocumentPhotoAddedUTC: String? = null,
    @SerializedName("firstName")
    var firstName: String? = "",
    @SerializedName("lastName")
    var lastName: String? = "",
    @SerializedName("issuer")
    var issuer: String? = "",
    @SerializedName("nationality")
    var nationality: String? = "",
    @SerializedName("gender")
    var gender: String? = "",
    @SerializedName("expieryDateTimeUTC")
    var expieryDateTimeUTC: String? = "",
    @SerializedName("dateTimeOfIssueUTC")
    var dateTimeOfIssueUTC: String? = null,
    @SerializedName("dateOfBirth")
    var dateOfBirth: String? = "",
    @SerializedName("isDirty")
    var isDirty: Boolean = false
) : Parcelable