package com.ops.airportr.common

class AppActionValues {
    companion object {
        const val COLLECTION_ACTION_VALUE = 12
        const val DELIVERY_ACTION_VALUE = 13
        const val ACCEPTANCE_ACTION_VALUE = 1
        const val STORAGE_ACTION_VALUE = 4
        const val INJECTION_ACTION_VALUE = 7
        const val REPATRAITION_ACTION_VALUE = 6
        const val VALIDATION_ERROR_CODE_VALUE = 215
        const val ADD_BOARDING_PASS_IMAGE = 38
        const val JOB_STARTED = 1
        const val JOB_COMPLETED = 5
        const val ADD_IATA_VALIDATION = 68
        const val ADD_LUGGAGE_IMAGE = 21
        const val ADD_IATA_JOB_TYPE = 13
        const val BAG_NOT_DAMAGE = 69
        const val Add_LUGGAGE_IMAGE = 21
        const val REPORT_DAMAGE = 33
        const val TAKE_PICTURE = 7
        const val Add_CUSTOMS_PAYMENT_RECEPIT_IMAGE = 59
        const val ADD_REPTARIATE_SIGNATURE_IMAGE = 23
        const val ADD_PHOTO_ID = 39
        const val ADD_PHOTO_ID_MANUAL = 85
        const val ADD_PASSPORT_IMAGE = 26
        const val ADD_PASSPORT_IMAGE_MANUAL = 84
        const val BAGS_PLACED_IN_STORAGE = 73
        const val BAGS_COLLECTED_FROM_STORAGE = 74
        const val IDENTIFY_BAG_SCAN = 75
        const val CUSTODY_BAG_SCAN = 76
        const val TAKE_CUSTODY_BAG_SCAN = 77
        const val FAST_INJECT_BAG_SCAN = 78
        const val PLACE_IN_STORAGE_BAG_SCAN = 79
        const val ACCEPT_SMART_BAG_SCAN = 80
        const val ACCEPT_BOOKING_BAG_SCAN = 81
        const val MissingBagsCarousel = 82
        const val MissingBagsPassengerCollected = 83
        const val DECLARATION_JOB = 8
        const val DISTANCE_LESS_THAN_75 = 71
        const val DISTANCE_GREATER_THAN_75 = 72
        const val NORMAL = 0
        const val PRE_ACCEPTANCE = 1
        const val HUB_DROP = 2
        const val INJECTED_USING_IATA_TAG = 51
        const val SCAN_CODE_TYPE_FOR_IATA = 1
        const val SCAN_CODE_TYPE_FOR_SEAL = 2
        const val CALL_COMPLETE = "1"
        const val CALL_FAIL = "2"

        //Todo: Notification
        const val FCM_V1 = "FcmV1"
        const val FCM = "4"
        const val BOOKING_CANCELLED_NOTIFICATION = "cancelled"
        const val BOOKING_INJECTION_NOTIFICATION = "injection"
        const val BOOKING_DELIVERY_NOTIFICATION = "delivery"
        const val BOOKING_COLLECTION_NOTIFICATION = "collection"
        const val CUSTOMER_SMS_REPLY = "customerSmsReply"
        //SMS Type
        const val CustomSMSText = "27"
        const val PreAcceptanceDriverLate = "44"
        const val PreAcceptanceParkingConfirmation = "45"
        const val PreAcceptanceLocationUnavailable = "46"
        const val PreAcceptanceAtDoorStep = "47"
        const val BOOKING_NOTE = "bookingNote"
    }
}