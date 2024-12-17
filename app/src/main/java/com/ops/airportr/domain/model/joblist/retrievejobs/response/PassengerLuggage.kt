package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PassengerLuggage(
    @SerializedName("passengerLuggageId")
    var passengerLuggageId: String? = "",
    @SerializedName("passengerLuggageImageId")
    var passengerLuggageImageId: List<String>? = listOf(),
    @SerializedName("dateTimeLuggageAddedUTC")
    var dateTimeLuggageAddedUTC: String? = "",
    @SerializedName("dateTimeLuggageModifiedUTC")
    var dateTimeLuggageModifiedUTC: String? = "",
    @SerializedName("bookingLuggageStatus")
    var bookingLuggageStatus: Int? = 0,
    @SerializedName("luggageActiveStatus")
    var luggageActiveStatus: Int? = 0,
    @SerializedName("bagHandlingType")
    var bagHandlingType: Int? = 0,
    @SerializedName("iataCode")
    var iataCode: String? = null,
    @SerializedName("iataCodeStatus")
    var iataCodeStatus: String? = null,
    @SerializedName("iataCodeSource")
    var iataCodeSource: String? = null,
    @SerializedName("iataCodeAddedDateTimeUTC")
    var iataCodeAddedDateTimeUTC: String? = null,
    @SerializedName("passengerLuggageCode")
    var passengerLuggageCode: String? = "",
    @SerializedName("weight")
    var weight: Double? = 0.0,
    @SerializedName("luggageType")
    var luggageType: Int? = 0,
    @SerializedName("bookingLuggageTamperAwareLiteSeals")
    var bookingLuggageTamperAwareLiteSeals: List<BookingLuggageTamperAwareLiteSeal>? = listOf(),
    @SerializedName("currentLuggageLocation")
    var currentLuggageLocation: GeoCoord? = GeoCoord(),
    @SerializedName("isDowngraded")
    var isDowngraded: Boolean? = false,
    @SerializedName("luggageCategory")
    var luggageCategory: Int? = 0,
    @SerializedName("mode")
    var mode: Int? = 0,
    @SerializedName("downgradeReason")
    var downgradeReason: Int? = 0,
    @SerializedName("otherText")
    var otherText: String? = null,
    @SerializedName("notes")
    var notes: String? = null,
    @SerializedName("isCommentedInFly")
    var isCommentedInFly: Boolean? = false,
    @SerializedName("xrayStatus")
    var xrayStatus: Int? = 0,
    @SerializedName("dcsStatus")
    var dcsStatus: String? = null,
    @SerializedName("bagClassification")
    var bagClassification: Int? = 0,
    @SerializedName("isIataValidated")
    var isIataValidated: Boolean? = false
) : Parcelable