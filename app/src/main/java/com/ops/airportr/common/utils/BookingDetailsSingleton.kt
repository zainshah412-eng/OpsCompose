package com.ops.airportr.common.utils

import android.util.Log
import com.ops.airportr.AppApplication
import com.ops.airportr.common.AppActionValues
import com.ops.airportr.domain.model.bookingdetails.BookingContact
import com.ops.airportr.domain.model.bookingdetails.BookingDetails
import com.ops.airportr.domain.model.bookingdetails.BookingJourneyDetail
import com.ops.airportr.domain.model.bookingdetails.CurrentChampion
import com.ops.airportr.domain.model.bookingdetails.DepartureAirport
import com.ops.airportr.domain.model.bookingdetails.DepartureDateTimeDetails
import com.ops.airportr.domain.model.bookingdetails.FlightInfo
import com.ops.airportr.domain.model.bookingdetails.FlightStatus
import com.ops.airportr.domain.model.bookingdetails.FlightStatusDetail
import com.ops.airportr.domain.model.bookingdetails.Job
import com.ops.airportr.domain.model.bookingdetails.Passenger
import com.ops.airportr.domain.model.bookingdetails.PassengerLuggage

class BookingDetailsSingleton {
    var bookingDetailFromDb: BookingDetails? = null
    var passengerLuggageList: ArrayList<PassengerLuggage>? = ArrayList()
    var passengersList: ArrayList<Passenger>? = ArrayList()
    var flightStatusDetailsList: ArrayList<FlightStatusDetail>? = ArrayList()
    var jobsList: ArrayList<Job>? = ArrayList()
    var currentChampions: ArrayList<CurrentChampion>? = ArrayList()

    var passenger: Passenger? = null
    var passengerLuggage: PassengerLuggage? = null

    var bookingContact: BookingContact? = null

    var flightStatus: FlightStatus? = null
    var flightInfo: FlightInfo? = null
    var flightStatusDetail: FlightStatusDetail? = null
    var flightStatusDetailLastStop: FlightStatusDetail? = null
    var departureAirport: DepartureAirport? = null
    var departureDateTimeDetails: DepartureDateTimeDetails? = null

    private var posPassenger: Int = -1
    private var posLuggage: Int = -1
    var bookingJourneyDetail: BookingJourneyDetail? = null


    private fun setUpData() {
        bookingDetailFromDb = AppApplication.sessionManager.bookingDetails
        if (bookingDetailFromDb != null) {
            bookingDetailFromDb?.let { bookingDetails ->
                bookingJourneyDetail = bookingDetails.bookingJourneyDetails[0]
                bookingJourneyDetail?.let {
                    passengersList = it.passengers
                    flightStatus = it.flightStatus
                    flightInfo = it.flightStatus?.flightInfo
                    flightStatusDetailsList = it.flightStatus?.flightStatusDetails
                    flightStatusDetail = flightStatusDetailsList?.get(0)
                    flightStatusDetailLastStop = flightStatusDetailsList?.last()
                    departureAirport = flightStatusDetail?.departureAirport
                    departureDateTimeDetails = flightStatusDetail?.departureDateTimeDetails
                    bookingContact = it.bookingContact
                    jobsList = it.jobs
                    for (obj in passengersList!!) {
                        obj.currentChampions?.let { it1 -> currentChampions!!.addAll(it1) }
                    }
                    for (obj in it.jobs!!) {
                        if (obj.jobType == 1) {
                            obj.currentChampion?.let { it1 ->
                                val data =
                                    currentChampions?.filter { it.championId == it1.championId }
                                if (data.isNullOrEmpty()) {
                                    currentChampions!!.add(it1)
                                }
                            }
                        }
                    }
                }
            }
        }

    }


    private fun setUpForValue() {
        bookingJourneyDetail?.let {
            passengerLuggageList = passengersList!![posPassenger].passengerLuggage
            passenger = it.passengers[posPassenger]
            passengerLuggage =
                passengersList!![posPassenger].passengerLuggage?.get(
                    posLuggage
                )
        }
    }

    fun init(passengerId: String? = "", passengerLuggageId: String? = "") {
        onDestroy()
        setUpData()
        if (passengerId.isNullOrEmpty() && passengerLuggageId.isNullOrEmpty()) {
            return
        }
        if ((passengersList?.size ?: 0) > 0) {
            for ((i, obj) in passengersList!!.withIndex()) {
                if (obj.passengerId == passengerId) {
                    posPassenger = i
                    for ((j, luggageObj) in obj.passengerLuggage!!.withIndex()) {
                        if (luggageObj.passengerLuggageId == passengerLuggageId) {
                            posLuggage = j
                        }
                    }
                }
            }
            Log.wtf("CheckLauanPass", "$posPassenger / $posLuggage")
            if (posLuggage != -1 && posPassenger != -1) {
                setUpForValue()
            }
        }
    }

    private fun resetPassengerLuaValues() {
        posPassenger = -1
        posLuggage = -1
    }

//    val scanLocationGeoCoordinates =
//        bookingJourneyDetail?.collectionLocation?.geoCoord?.let { geoCoder ->
//            geoCoder.latitude?.let { lat ->
//                geoCoder.longitude?.let { lon ->
//                    ScanLocationGeocoordinates(
//                        lat, lon
//                    )
//                }
//            }
//        }


    fun getDateTimeFromJobsList(actionValue: Int): String? {
        jobsList?.let { list ->
            list.forEach { it ->
                if (it.jobType == actionValue) {
                    return it.startDueDateTimeUTC
                }
            }
        }

        return ""
    }

    fun getJobNumber(jobType: Int): String {
        return bookingJourneyDetail?.jobs?.singleOrNull {
            it.jobType == jobType
        }?.jobNumber ?: ""

    }

    fun getFlightFinalDestination(): FlightStatusDetail? {
        return flightStatusDetailsList?.singleOrNull { it.isFinalDestination }
    }

    fun getBookingContactNum(): String {
        bookingContact?.let {
            with(it) {
                return "+${countryCode ?: ""} ${phoneNumber ?: ""}"
            }
        }
        return ""
    }

    fun getBookingName(): String {
        bookingContact?.let {
            with(it) {
                return "${firstName ?: ""} ${lastName ?: ""}"
            }
        }
        return ""
    }

    fun getBookingEmail(): String {
        bookingContact?.let {
            with(it) {
                return emailAddress ?: ""
            }
        }
        return ""
    }

    fun getIataCode(): String {
        return passengerLuggage?.iataCode.toString()
    }

    fun onDestroy() {
        bookingDetailFromDb = null
        passengerLuggageList = ArrayList()
        passengersList = ArrayList()
        flightStatusDetailsList = ArrayList()
        jobsList = ArrayList()
        currentChampions = ArrayList()

        passenger = null
        passengerLuggage = null

        bookingContact = null

        flightStatus = null
        flightInfo = null
        flightStatusDetail = null
        flightStatusDetailLastStop = null
        departureAirport = null
        departureDateTimeDetails = null

        posPassenger = -1
        posLuggage = -1
        bookingJourneyDetail = null
    }

    fun getJobObject(jobType: Int): Job? {
        bookingDetailFromDb = AppApplication.sessionManager.bookingDetails
        for (obj in bookingDetailFromDb!!.bookingJourneyDetails[0].jobs!!) {
            if (obj.jobType == AppActionValues.ACCEPTANCE_ACTION_VALUE) {
                return obj
            }
        }
        return null
    }
}