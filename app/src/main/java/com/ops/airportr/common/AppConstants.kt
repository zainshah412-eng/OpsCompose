package com.ops.airportr.common
import java.util.regex.Pattern
import android.text.InputFilter

import com.ops.airportr.BuildConfig
object AppConstants {

    const val UAT_SUBSCRIPTION_KEY = BuildConfig.UAT_SUBSCRIPTION_KEY
    const val DEV_SUBSCRIPTION_KEY = BuildConfig.DEV_SUBSCRIPTION_KEY
    const val LIVE_SUBSCRIPTION_KEY = BuildConfig.LIVE_SUBSCRIPTION_KEY

    const val AMPLITUDE_KEY = BuildConfig.AMPLITUDE_KEY // dev
    const val IMAGE_AUTHORITY = BuildConfig.IMAGE_AUTHORITY // UAT
    const val CLARITY_ = BuildConfig.CLARITY_ //UAT
    const val TWILIO = BuildConfig.TWILIO //UAT
    const val EMAILS = BuildConfig.EMAILS //UAT
    const val MAPBOX = BuildConfig.MAPBOX_KEY //UAT

    const val AZURE_STORAGE_BASE_URL = "https://portrdevimagestorage.blob.core.windows.net/"
    const val SMS_CHAT_URL_DEV = "https://pdev-comms-microservice.azurewebsites.net/"
    const val SMS_CHAT_URL_UAT = "https://puat-comms-microservice.azurewebsites.net/"
    const val SMS_CHAT_URL_LIVE = "https://portr-comms-microservice.azurewebsites.net/"
    const val AZURE_STORAGE_CONTAINER_NAME = "app-videos"

    const val AZURE_SAS_TOKEN =
        "sv=2020-08-04&st=2024-04-23T09%3A03%3A36Z&se=2026-01-17T10%3A03%3A00Z&sr=c&sp=racwl&sig=53EbHVQW384B1X%2FofDC5AqPphMVMXWGCqZwFCMnmyqg%3D"
    const val SHOW_CONSOLE_LOGS = androidx.viewbinding.BuildConfig.DEBUG
    const val DELAY_TIME = 3 * 1000L // 2 sec of delay
    val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9!@#$]{8,24}"
    )

    val EMAIL_ADDRESS: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\'\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    val letterFilter =
        InputFilter { source, start, end, _, _, _ ->
            var filtered = ""
            for (i in start until end) {
                val character = source[i]
                if (Character.isSpaceChar(character) || Character.isLetter(character)) {
                    filtered += character
                }
            }
            filtered
        }

    /**
     * Base and staging CCD URLS
     */
    const val PRODUCTION_URL_CCD = BuildConfig.PRODUCTION_URL_CCD_LIVE // Live
    const val PRODUCTION_URL_CCD_DEV = BuildConfig.PRODUCTION_URL_CCD_DEV // Dev
    const val PRODUCTION_URL_CCD_UAT = BuildConfig.PRODUCTION_URL_CCD_UAT // UAT


    /**
     * EndPoints
     */
    private const val API_COMMS = "Comms/api"
    private const val API = "api/"
    private const val API_END_WEB = "Web/api"
    private const val API_END_POINT_CCD = "Mobile/api"
    private const val API_END_POINT_CCD_PAYMENT = "Payment/api"
    const val RETRIEVE_JOBS_CCD =
        "$API_END_POINT_CCD/ArrivalsJob/RetrieveJobs"
    const val UPLOAD_IMAGE_CCD =
        "$API_END_POINT_CCD/Job/ApplyImageActionUpdateNew"
    const val UPDATE_JOB_ACTION_CCD =
        "$API_END_POINT_CCD/Job/UpdateJobActions"
    const val APPLY_ACTION_UPDATE_NEW_CCD =
        "$API_END_POINT_CCD/Job/ApplyActionUpdateNew"
    const val APPLY_ACTION_UPDATE_NEW_SCAN_CCD =
        "$API_END_POINT_CCD/Job/ApplyActionUpdateNew"
    const val UPDATE_JOB_CCD = "$API_END_POINT_CCD/Job/UpdateJob"
    const val BOOKING_SUMMARY_FLIGHT_CCD =
        "$API_END_POINT_CCD/Arrival/GetBookingSummaryByFlight"
    const val ARRIVAL_BOOKING_SUMMARY_FLIGHT_CCD =
        "$API_END_POINT_CCD/ArrivalBooking/GetBookingSummaryByFlight"
    const val GET_FLIGHT_BY_AIRPORT_TERMINALS_CCD =
        "$API_END_POINT_CCD/Arrival/GetFlightsByAirportTerminal"
    const val GET_BOOKING_SUMMARY_BY_DELIVERY_GROUP_CCD =
        "$API_END_POINT_CCD/Arrival/GetBookingSummaryByDeliveryGroup"
    const val GET_BOOKING_SUMMARY_BY_COLLECTION_GROUP_CCD =
        "$API_END_POINT_CCD/Arrival/GetBookingSummaryByAirportCollectionGroup"
    const val GET_LOCATIONS_CCD =
        "$API_END_POINT_CCD/Location/GetLocations"
    const val GET_BOOKING_AT_STORAGE_LOCATION_CCD =
        "$API_END_POINT_CCD/Arrival/GetBookingSummaryById"
    const val ADD_BAGS_CCD =
        "$API_END_POINT_CCD/CarouselBaggage/AddBag"
    const val REMOVE_BAG_CCD =
        "$API_END_POINT_CCD/Booking/RemovePassengerLuggage"
    const val EXCESS_BAG_CCD =
        "$API_END_POINT_CCD_PAYMENT/Payment/PayForExcessBag"
    const val PLACE_BAG_STORAGE_CCD =
        "$API_END_POINT_CCD/CarouselBaggage/PlaceBagToStorage"
    const val TAKE_BAG_CUSTODY_CCD =
        "$API_END_POINT_CCD/CarouselBaggage/TakeBagCustody"
    const val GET_IMAGE_URL_CCD =
        "$PRODUCTION_URL_CCD$API_END_POINT_CCD/Image/GetImageUrl"
    const val UPDATE_USER_LOCATION_CCD =
        "$PRODUCTION_URL_CCD$API_END_POINT_CCD/UpdateUserLocation"

    const val GET_ACTION_UPDATE_LOG_CCD =
        "$API_END_POINT_CCD/Job/GetActionUpdateLogs"
    const val GET_BOOKING_DETAIL_CCD =
        "$API_END_POINT_CCD/Booking/GetBookingDetails"
    const val GET_BOOKING_FLIGHTS_BY_AIRPORT_TERMINAL_CCD =
        "$API_END_POINT_CCD/BookingFlight/GetBookingFlightsByAirportTerminal"
    const val BOOKING_SUMMARY_GET_BOOKING_SUMMARY_BY_FLIGHT =
        "$API_END_POINT_CCD/BookingSummary/GetBookingSummaryByFlight"
    const val CANCEL_BOOKING_ABC = "$API_END_WEB/Booking/CancelBooking"
    const val SEND_E_MAIL = "$API_COMMS/Comms/SendEmail"
    const val GET_MESSAGES = "$API_COMMS/Comms/GetMessageInstances"
    const val SMS_CHAT = API + "Comms/Post"

    /**
     * Versions Urls
     */
    const val GET_DEV_APP_VERSIONS = "$API_END_POINT_CCD/DevAndroidAppVersions"
    const val GET_UAT_APP_VERSIONS = "$API_END_POINT_CCD/UATAndroidAppVersions"
    const val GET_LIVE_APP_VERSIONS = "$API_END_POINT_CCD/LiveAndroidAppVersions"

    /**
     * Base and staging URLS
     */
    const val PRODUCTION_URL_LIVE = BuildConfig.PRODUCTION_URL_ABC_LIVE
    const val PRODUCTION_URL_UAT = BuildConfig.PRODUCTION_URL_ABC_UAT
    const val PRODUCTION_URL_DEV = BuildConfig.PRODUCTION_URL_ABC_DEV
    const val IMAGE_URL_DEV = "https://portrdevimagestorage.blob.core.windows.net/app-images/"
    const val IMAGE_URL_UAT = "https://portruatimagestorage.blob.core.windows.net/app-images/"
    const val IMAGE_URL_LIVE = "https://portrliveimagestorage.blob.core.windows.net/app-images/"
    const val BLOB_STORAGE_URL = "https://portrdevimagestorage.blob.core.windows.net/app-videos"

    /**
     * EndPoints
     */
    const val API_END_POINT = "api/"
    const val USER_END_POINT = "User/"
    const val TOKEN_ENDPOINT = "token"
    const val BOOKING_LIST_API = PRODUCTION_URL_UAT + "login"
    const val GET_CURRENT_USER_API =
        API_END_POINT + USER_END_POINT + "GetCurrentUser"
    const val GET_CURRENT_USER_API_CCD =
        API_END_POINT + "/" + USER_END_POINT + "GetCurrentUser"
    const val REGISTER_DEVICE = API_END_POINT + "NotificationHub/RegisterFCMV1Device"
    const val GET_USERS_LIST = PRODUCTION_URL_UAT + API_END_POINT + USER_END_POINT + "GetUsers"
    const val BOOKINGS_LIST_DATA =
        API_END_POINT + "BookingData/GetBookingSummariesMobile"
    const val UPDATE_USER_CURRENT_LOCATION =
        API_END_POINT + USER_END_POINT + "UpdateUserLocation"
    const val UPDATE_ACCEPTANCE_LOCK =
        API_END_POINT + "Tracking/GetAcceptanceLockStatus"
    const val GET_BOOKING_DETAIL = API_END_POINT + "Booking/GetBookingDetails"
    const val VALIDATE_PASSPORT =
        API_END_POINT + "DCS/ValidatePassengerPassport"
    const val ADD_PASSENGER_LIST =
        API_END_POINT + "Booking/AddPassenger"
    const val UPDATE_JOB = API_END_POINT + "Job/UpdateJob"
    const val REMOVE_PASSENGER = API_END_POINT + "Booking/RemovePassenger"
    const val SELF_SERVICE_DATA =
        API_END_POINT + "SelfService/GetSelfServiceData"
    const val SET_QUESTIONS_ANSWERS =
        API_END_POINT + "Communication/StoreCustomerQuestionAnswerResponse"
    const val UPDATE_PHONE_NUMBER =
        API_END_POINT + "Booking/AmendBookingContact"
    const val ADD_BOOKING_NOTE = API_END_POINT + "Communication/AddBookingNote"
    const val ADD_PASSENGER_LUGGAGE =
        API_END_POINT + "Booking/AddPassengerLuggageItems"
    const val UPLOAD_IMAGE = API_END_POINT + "Job/ApplyImageActionUpdateNew"
    const val REMOVE_PASSENGER_LUGGAGE =
        API_END_POINT + "Booking/RemovePassengerLuggage"
    const val GET_IMAGE = API_END_POINT + "Image/GetImageUrl"
    const val REMOVE_IMAGE = API_END_POINT + "Image/RemoveImage"
    const val APPLY_ACTION_UPDATE_NEW_ABC = API_END_POINT + "Job/ApplyActionUpdateNew"
    const val APPLY_ACTION_UPDATE_NEW_SCAN_ABC = API_END_POINT + "Job/ApplyActionUpdateNew"
    const val UPDATE_JOB_ACTION = API_END_POINT + "Job/UpdateJobActions"
    const val GET_ACTION_UPDATE_LOG = API_END_POINT + "Job/GetActionUpdateLogs"
    const val UPDATE_PASSENGER_VALIDATION_FLAG =
        API_END_POINT + "Booking/UpdatePassengerValidationFlag"
    const val RESET_PASSWORD =
        API_END_POINT + USER_END_POINT + "SendResetPasswordLink"
    const val ADD_USER_IMAGE = API_END_POINT + "Image/AddImageWithUserId"
    const val UPDATE_USER = API_END_POINT + USER_END_POINT + "UpdateUser"
    const val GET_ALL_AIRPORTS = API_END_POINT + "Location/GetAllAirports"
    const val RETRIEVE_JOBS = API_END_POINT + "Job/Retrievejobs"
    const val GET_BOOKING_LUGGAGE = API_END_POINT + "BookingData/GetBookingLuggages"
    const val GET_BOOKING_NOTES_BY_BOOKING_REFERENCE =
        API_END_POINT + "Communication/GetBookingNoteByBookingReference"
    const val MAKE_EXCESS_CHARGES_PAYMENT =
        API_END_POINT + "Payment/MakeExcessBaggagePayment"
    const val Downgrade_Passenger_Luggage =
        "$API_END_POINT/Job/DowngradePassengerLuggage"
    const val Validate_Passenger_PNR =
        "$API_END_POINT/DCS/ValidatePassengerPNR"
    const val AMADEUS_IATA_PRINT =
        API_END_POINT + "DCS/PrintTag"
    const val ASSIGN = API_END_POINT + "JobScanning/AssignBag"
    const val GET_BAG_ALLOWANCE = API_END_POINT + "DCS/GetBagAllowance"
    const val GET_LOCATIONS_ABC =
        API_END_POINT + "Location/GetLocations"
    const val GET_BOOKING_AT_STORAGE_LOCATION_ABC =
        API_END_POINT + "BookingSummary/GetBookingSummaryById"
    const val TAKE_BAG_CUSTODY_ABC =
        API_END_POINT + "Storage/TakeBagCustody"
    const val PLACE_BAG_STORAGE_ABC =
        API_END_POINT + "Storage/PlaceBagToStorage"
    const val DELETE_IATA_TAG = API_END_POINT + "DCS/DeleteTag"
    const val UPDATE_PASSENGER_VALIDATE_FLAG =
        API_END_POINT + "Booking/UpdatePassengerValidationFlag"
    const val UPDATE_PASSENGER_LUGGAGE =
        API_END_POINT + "Booking/UpdatePassengerLuggage"
    const val STORE_CUSTOMER_QUESTION_ANSWER_RESPONSE =
        API_END_POINT + "Communication/StoreCustomerQuestionAnswerResponse"
    const val GET_COMMUNICATION_LOG_CCD =
        "$API_END_POINT/Communication/GetCommunicationLog"
    const val STORE_TRACKING_DATA = API_END_POINT_CCD + "/Tracking/StoreTrackingData"
    const val SEND_LOGIN_CODE = API_END_WEB + "/SMS/SendOneTimeLoginCode"
    const val VALIDATE_LOGIN_CODE = API_END_WEB + "/User/ValidateSMSCode"
    const val SEND_DEVICE_DATA = API_END_POINT + "Job/SaveDeviceData"


}