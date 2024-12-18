package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BookingJourneyDetail(
    @SerializedName("bookingReference")
    var bookingReference: String? = null,
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String? = null,
    @SerializedName("journeyStatus")
    var journeyStatus: Int = 0,
    @SerializedName("journeyDirection")
    var journeyDirection: Int = 0,
    @SerializedName("journeyActivityStatus")
    var journeyActivityStatus: Int = 0,
    @SerializedName("trackingStatus")
    var trackingStatus: Int = 0,
    @SerializedName("bookingJourneyActivity")
    var bookingJourneyActivity: Int = 0,
    @SerializedName("isCancelled")
    var isCancelled: Boolean = false,
    @SerializedName("paymentDetails")
    var paymentDetails: PaymentDetails = PaymentDetails(),
    @SerializedName("collectionLocation")
    var collectionLocation: CollectionLocation = CollectionLocation(),
    @SerializedName("collectionDateTimeUTC")
    var collectionDateTimeUTC: String? = null,
    @SerializedName("deliveryLocation")
    var deliveryLocation: DeliveryLocation = DeliveryLocation(),
    @SerializedName("deliveryDateTimeUTC")
    var deliveryDateTimeUTC: String? = "",
    @SerializedName("passengers")
    var passengers: ArrayList<Passenger> = ArrayList(),
    @SerializedName("hotelBookingLeadPassenger")
    var hotelBookingLeadPassenger: String? = null,
    @SerializedName("alarmOverview")
    var alarmOverview: AlarmOverview = AlarmOverview(),
    @SerializedName("addOnProductsOverview")
    var addOnProductsOverview: ArrayList<AddOnProductsOverview> = ArrayList(),
    @SerializedName("bookingLuggageItemScanDetails")
    var bookingLuggageItemScanDetails: ArrayList<String>? = ArrayList(),
    @SerializedName("bookingNotes")
    var bookingNotes: ArrayList<BookingNotes> = ArrayList(),
    @SerializedName("jobs")
    var jobs: ArrayList<Job>? = ArrayList(),
    @SerializedName("currentJob")
    var currentJob: String? = "",
    @SerializedName("flightStatus")
    var flightStatus: FlightStatus? = FlightStatus(),
    @SerializedName("journeyLinkedImages")
    var journeyLinkedImages: ArrayList<JourneyLinkedImage>? = ArrayList(),
    @SerializedName("bagLiveLocations")
    var bagLiveLocations: ArrayList<BagLiveLocation>? = ArrayList(),
    @SerializedName("slotDurationMinutes")
    var slotDurationMinutes: Int = 0,
    @SerializedName("isAirportToAirport")
    var isAirportToAirport: Boolean = false,
    @SerializedName("bookingContact")
    var bookingContact: BookingContact? = BookingContact(),
    @SerializedName("numberOfBags")
    var numberOfBags: Int = 0,
    @SerializedName("numberOfIncludedBags")
    var numberOfIncludedBags: Int = 0,
    @SerializedName("isDomesticABC")
    var isDomesticABC: Boolean = false,
    @SerializedName("excessBaggagePayment")
    var excessBaggagePayment: ExcessBaggagePayment? = null,
    @SerializedName("extraBagPayment")
    val extraBagPayment: ExtraBagPayment? = null,
    @SerializedName("xrayStatus")
    var xrayStatus: Int = 0,
    @SerializedName("isE2EEligible")
    var isE2EEligible: Boolean = false,
    @SerializedName("findTravellerStatus")
    var findTravellerStatus: Int = 0,
    @SerializedName("passengerPolicy")
    var passengerPolicy: PassengerPolicy? = PassengerPolicy(),
    @SerializedName("dcsEligibility")
    var dcsEligibility: Int = 0,
    @SerializedName("pnr")
    var pnr: String? = null,
    @SerializedName("dcsStatus")
    var dcsStatus: String? = null,
    @SerializedName("isDirectInjection")
    var isDirectInjection: Boolean = false,
    @SerializedName("isEndToEnd")
    var isEndToEnd: Boolean = false,
    @SerializedName("tagPrintingAvailability")
    var tagPrintingAvailability: Int = 0,
    @SerializedName("acceptanceMode")
    var acceptanceMode: Int = 0,
    @SerializedName("hasOversizedBag")
    var hasOversizedBag: Boolean = false,
    @SerializedName("customsDeclarationSigned")
    var customsDeclarationSigned: Boolean = false,
    @SerializedName("isConditionalAcceptance")
    var isConditionalAcceptance: Boolean = false,
    @SerializedName("isPreAcceptanceEligible")
    var isPreAcceptanceEligible: Boolean = false,
    @SerializedName("preAcceptanceType")
    var preAcceptanceType: Int? = 0,
    @SerializedName("bagsCount")
    var bagsCount: Int? = 0,
    @SerializedName("collectedBagsCount")
    var collectedBagsCount: Int? = 0,
    @SerializedName("childEquipmentCount")
    var childEquipmentCount: Int? = 0,
    @SerializedName("oversizeItemCount")
    var oversizeItemCount: Int? = 0,
    @SerializedName("preAcceptanceCompleted")
    var preAcceptanceCompleted: Boolean = false,
    @SerializedName("virtualBagClassification")
    var virtualBagClassification: ArrayList<VirtualBagClassification>? = ArrayList(),
) : Parcelable