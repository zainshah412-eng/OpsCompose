package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PassengerValidationStatusFlags(
    @SerializedName("journeyLinkedPassengerId")
    var journeyLinkedPassengerId: String? = "",
    @SerializedName("isPassengerPresent")
    var isPassengerPresent: Int = 0,
    @SerializedName("hasValidId")
    var hasValidId: Int = 0,
    @SerializedName("firstNameChecked")
    var firstNameChecked: Int = 0,
    @SerializedName("lastNameChecked")
    var lastNameChecked: Int = 0,
    @SerializedName("idPhotoChecked")
    var idPhotoChecked: Int = 0,
    @SerializedName("hasBoardingPass")
    var hasBoardingPass: Int = 0,
    @SerializedName("numberOfInjectBagsAllowed")
    var numberOfInjectBagsAllowed: Int = 0,
    @SerializedName("maximumWeightAllowed")
    var maximumWeightAllowed: Int = 0,
    @SerializedName("idScanned")
    var idScanned: Int = 0,
    @SerializedName("boardingPassScanned")
    var boardingPassScanned: Int = 0,
    @SerializedName("withinWeightAllowance")
    var withinWeightAllowance: Int = 0,
    @SerializedName("hasIdDocumentImage")
    var hasIdDocumentImage: Int = 0,
    @SerializedName("hasBoardingPassImage")
    var hasBoardingPassImage: Int = 0,
    @SerializedName("verifiedFlightDetails")
    var verifiedFlightDetails: Int = 0,
    @SerializedName("beenGivenItemToCarryForSomeoneElse")
    var beenGivenItemToCarryForSomeoneElse: Int = 0,
    @SerializedName("hasLuggageBeenInterferedWith")
    var hasLuggageBeenInterferedWith: Int = 0,
    @SerializedName("carryingDangerousItems")
    var carryingDangerousItems: Int = 0,
    @SerializedName("carryingFireArms")
    var carryingFireArms: Int = 0,
    @SerializedName("airlinePassengerDocumentVerified")
    var airlinePassengerDocumentVerified: Int = 0,
    @SerializedName("airlinePassengerReservationVerified")
    var airlinePassengerReservationVerified: Int = 0,
    @SerializedName("airlinePassengerCheckInVerified")
    var airlinePassengerCheckInVerified: Int = 0
) : Parcelable