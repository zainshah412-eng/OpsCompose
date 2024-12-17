package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class BookingInformation(
    @SerializedName("bookingReference")
    var bookingReference: String? = "",
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String? = "",
    @SerializedName("journeyStatus")
    var journeyStatus: Int? = 0,
    @SerializedName("journeyDirection")
    var journeyDirection: Int? = 0,
    @SerializedName("journeyActivityStatus")
    var journeyActivityStatus: Int? = 0,
    @SerializedName("trackingStatus")
    var trackingStatus: Int? = 0,
    @SerializedName("bookingJourneyActivity")
    var bookingJourneyActivity: Int? = 0,
    @SerializedName("isCancelled")
    var isCancelled: Boolean? = false,
    @SerializedName("collectionLocation")
    var collectionLocation: CollectionLocation? = CollectionLocation(),
    @SerializedName("collectionDateTimeUTC")
    var collectionDateTimeUTC: String? = "",
    @SerializedName("deliveryLocation")
    var deliveryLocation: DeliveryLocation? = DeliveryLocation(),
    @SerializedName("totalBookedBags")
    var totalBookedBags: Int? = 0,
    @SerializedName("bookingCreatorUser")
    var bookingCreatorUser: BookingCreatorUser? = BookingCreatorUser(),
    @SerializedName("deliveryDateTimeUTC")
    var deliveryDateTimeUTC: String? = "",
    @SerializedName("passengers")
    var passengers: ArrayList<Passenger>? = ArrayList(),
    @SerializedName("addOnProductsOverview")
    var addOnProductsOverview: List<AddOnProductsOverview>? = listOf(),
    @SerializedName("journeyLinkedImages")
    var journeyLinkedImages: List<JourneyLinkedImage>? = listOf(),
    @SerializedName("bagLiveLocations")
    var bagLiveLocations: ArrayList<BagLiveLocation>? = ArrayList(),
    @SerializedName("slotDurationMinutes")
    var slotDurationMinutes: Int? = 0,
    @SerializedName("isAirportToAirport")
    var isAirportToAirport: Boolean? = false,
    @SerializedName("hasOversizedBag")
    var hasOversizedBag: Boolean? = false,
    @SerializedName("dcsEligibility")
    var dcsEligibility: Int = 0,
    @SerializedName("virtualBagClassification")
    var virtualBagClassification: ArrayList<VirtualBagClassification>? = ArrayList(),

    ) : Parcelable