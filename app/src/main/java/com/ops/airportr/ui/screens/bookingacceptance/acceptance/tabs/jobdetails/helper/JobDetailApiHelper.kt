package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.helper

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.ops.airportr.AppApplication
import com.ops.airportr.BuildConfig
import com.ops.airportr.common.AppActionValues
import com.ops.airportr.common.AppConstants
import com.ops.airportr.common.utils.BookingDetailsSingleton
import com.ops.airportr.common.utils.extension.compareDates
import com.ops.airportr.common.utils.extension.formatDuration
import com.ops.airportr.common.utils.extension.getCurrentTimeStampIntoFormat
import com.ops.airportr.common.utils.extension.getNetworkType
import com.ops.airportr.common.utils.extension.urlForAcceptance
import com.ops.airportr.common.utils.extension.urlForCCD
import com.ops.airportr.domain.model.acceptance.UpdateAcceptanceParam
import com.ops.airportr.domain.model.applyactionupdate.params.ActionInformation
import com.ops.airportr.domain.model.applyactionupdate.params.ActionUpdateBooking
import com.ops.airportr.domain.model.applyactionupdate.params.ApplyActionUpdateSealParams
import com.ops.airportr.domain.model.applyactionupdate.params.ScanLocationGeocoordinates
import com.ops.airportr.domain.model.senddevicedata.SendDeviceDataParam
import com.ops.airportr.domain.model.storetrackingdata.StoreTrackingDataParams
import com.ops.airportr.domain.model.updatejob.UpdateJobParam
import com.ops.airportr.domain.model.updatelogs.params.GetActionUpdateLogsParams
import com.ops.airportr.domain.model.user.GeoCoord
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.JobDetailsScreenViewModel
import java.util.ArrayList

class JobDetailApiHelper() {
    fun callTrackData(
        context: Context,
        viewModel: JobDetailsScreenViewModel,
        durationSlot: Double
    ) {
        // Update the timer display with remaining seconds
        try {
            var lastUserLocation = AppApplication.sessionManager.getLastUserLocation
            if (lastUserLocation != null) {
                val currentLat = AppApplication.sessionManager.getLastUserLocation.latitude ?: 0.0
                val currentLng = AppApplication.sessionManager.getLastUserLocation.longitude ?: 0.0
                val activeBooking = AppApplication.sessionManager.activeBookingDetails
                val duration = context.formatDuration(
                    AppApplication.sessionManager.duration.toDouble()
                )

                var model = StoreTrackingDataParams(
                    activeBooking?.bookingJourneyDetails?.get(0)?.bookingJourneyReference,
                    activeBooking?.bookingReference,
                    10,
                    GeoCoord(
                        currentLat,
                        currentLng
                    )
                )

                viewModel.storeTrackingData(
                    context.urlForCCD() + AppConstants.STORE_TRACKING_DATA,
                    model
                )
            }
        } catch (e: Exception) {
            Log.wtf("TRACCK_ERROOR", "")

        }
    }

    fun applyActionUpdateNewData(context: Context,
                                 viewModel: JobDetailsScreenViewModel,
                                 bookingDetails: BookingDetailsSingleton) {
        val actionInformationList = ArrayList<ActionInformation>()
        var jobNumber = ""
        for ((index, jobObj) in bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.jobs?.withIndex()!!) {
            if (jobObj.jobType == AppActionValues.ACCEPTANCE_ACTION_VALUE) {
                jobNumber = jobObj.jobNumber.toString()
            }
        }
        val scanLocationGeoCoordinates =
            bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.collectionLocation?.geoCoord?.latitude?.let {
                bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.collectionLocation?.geoCoord?.longitude?.let { it1 ->
                    ScanLocationGeocoordinates(
                        it,
                        it1

                    )
                }
            }

        val actionValues = ArrayList<String>()
        actionValues.add(
            "Acceptance time lock override\nArrived " +
                    "${bookingDetails.bookingJourneyDetail?.collectionDateTimeUTC?.compareDates()}early"
        )

        val actionInformationListInner = ArrayList<ActionInformation>()
        val actionInformation = ActionInformation(
            "",
            0,
            43,
            actionValues,
            1
        )
        actionInformation?.let { actionInformationListInner.add(it) }
        val actionUpdateBooking =
            bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.bookingReference?.let {
                AppApplication.sessionManager.userDetails.userId?.let { it1 ->
                    bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.bookingJourneyReference?.let { it2 ->
                        bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.deliveryLocation?.locationId?.let { it3 ->
                            scanLocationGeoCoordinates?.let { it4 ->
                                bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.deliveryLocation?.locationType?.let { it5 ->
                                    ActionUpdateBooking(
                                        it,
                                        it2,
                                        getCurrentTimeStampIntoFormat(),
                                        jobNumber,
                                        it3,
                                        it4,
                                        getCurrentTimeStampIntoFormat(),
                                        "",
                                        it5,
                                        actionInformationListInner
                                    )
                                }
                            }
                        }
                    }
                }
            }

        val actionUpdateBookingList = ArrayList<ActionUpdateBooking>()
        actionUpdateBooking?.let { actionUpdateBookingList.add(it) }

        val params =
            bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.deliveryLocation?.locationType
                ?.let {
                    ApplyActionUpdateSealParams(
                        bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.bookingReference
                            ?: "",
                        jobNumber,
                        AppApplication.sessionManager.userDetails.userId!!,
                        0,
                        "",
                        actionInformationList,
                        actionUpdateBookingList,
                        ""
                    )
                }
        if (params != null) {
            viewModel.applyActionUpdateNew(
                context.urlForAcceptance() + AppConstants.APPLY_ACTION_UPDATE_NEW_ABC,
                params
            )
        }
    }

    fun smsDeviceData(context: Context,
                      viewModel: JobDetailsScreenViewModel,
                      bookingDetails: BookingDetailsSingleton){

        val sendDeviceDataParam = SendDeviceDataParam(
            BuildConfig.VERSION_NAME,
            bookingDetails.bookingJourneyDetail?.bookingReference,
            getCurrentTimeStampIntoFormat(),
            Build.VERSION.RELEASE ?: "Unknown",
            "Android",
            getNetworkType(context),
            "Acceptance"
        )

        viewModel.smsDeviceData(
            context.urlForAcceptance() + AppConstants.SEND_DEVICE_DATA,
            sendDeviceDataParam
        )

    }

     fun updateJobForTracking(context: Context,
                              viewModel: JobDetailsScreenViewModel,
                              bookingDetails: BookingDetailsSingleton,
                              actionType: Int) {
        try {
          var  lastUserLocation = AppApplication.sessionManager.getLastUserLocation
            if (lastUserLocation != null) {
                val currentLat = AppApplication.sessionManager.getLastUserLocation.latitude ?: 0.0
                val currentLng = AppApplication.sessionManager.getLastUserLocation.longitude ?: 0.0

                val actionInformationList = ArrayList<ActionInformation>()
                var jobNumer = ""
                for ((index, jobObj) in bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.jobs?.withIndex()!!) {
                    if (jobObj.jobType == 1) {
                        jobNumer = jobObj.jobNumber.toString()
                    }
                }

                val scanLocationGeocoordinates =
                    ScanLocationGeocoordinates(
                        currentLat,
                        currentLng
                    )

                val actionValues = ArrayList<String>()

                val actionInformationListInner = ArrayList<ActionInformation>()
                val actionInformation = ActionInformation(
                    null,
                    null,
                    actionType,
                    actionValues,
                    null
                )
                actionInformation.let { actionInformationListInner.add(it) }

                val actionUpdateBooking =
                    bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.bookingReference?.let {
                        AppApplication.sessionManager.userDetails.userId?.let { it1 ->
                            bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.bookingJourneyReference?.let { it2 ->
                                bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.deliveryLocation?.locationId?.let { it3 ->
                                    scanLocationGeocoordinates.let { it4 ->
                                        bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.deliveryLocation?.locationType?.let { it5 ->
                                            ActionUpdateBooking(
                                                it,
                                                it2,
                                                getCurrentTimeStampIntoFormat(),
                                                jobNumer,
                                                it3,
                                                it4,
                                                getCurrentTimeStampIntoFormat(),
                                                "",
                                                it5,
                                                actionInformationListInner
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                val actionUpdateBookingList = ArrayList<ActionUpdateBooking>()
                actionUpdateBooking?.let { actionUpdateBookingList.add(it) }

                val params =
                    bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.deliveryLocation?.locationType
                        ?.let {
                            ApplyActionUpdateSealParams(
                                bookingDetails.bookingDetailFromDb?.bookingJourneyDetails?.get(0)?.bookingReference?:"",
                                jobNumer,
                                AppApplication.sessionManager.userDetails.userId!!,
                                0,
                                "",
                                actionInformationList,
                                actionUpdateBookingList,
                                ""
                            )
                        }
                if (params != null) {
                    viewModel.trackingUser(
                        context.urlForAcceptance() + AppConstants.APPLY_ACTION_UPDATE_NEW_ABC,
                        params
                    )
                }
            }
        } catch (e: Exception) {
            Log.wtf("Excception:", e.toString())
        }
    }

    fun updateAcceptanceLock(context: Context,
                             viewModel: JobDetailsScreenViewModel,
                             bookingDetails: BookingDetailsSingleton){
        viewModel.updateAcceptanceLock(
            context.urlForAcceptance() + AppConstants.UPDATE_ACCEPTANCE_LOCK,
            UpdateAcceptanceParam(
                bookingDetails.bookingJourneyDetail?.bookingReference ?: ""
            )
        )
    }

    fun updateJob(context: Context,
                  viewModel: JobDetailsScreenViewModel,
                  bookingDetails: BookingDetailsSingleton){
        var jobIdAgainstAcceptance = ""
        for (obj in bookingDetails.bookingJourneyDetail?.jobs!!) {
            if (obj.jobType == 1)
                jobIdAgainstAcceptance = obj.jobId.toString()
        }
        AppApplication.sessionManager.userDetails.userId?.let { it1 ->
            viewModel.updateJob(
                context.urlForAcceptance() + AppConstants.UPDATE_JOB,
                UpdateJobParam(
                    it1,
                    jobIdAgainstAcceptance
                )
            )
        }
    }
    fun getActionUpdateLog(context: Context,
                           viewModel: JobDetailsScreenViewModel,
                           bookingDetails: BookingDetailsSingleton){
        bookingDetails.bookingDetailFromDb?.bookingReference?.let { bookRef ->
            bookingDetails.bookingDetailFromDb!!.bookingJourneyDetails[0].bookingJourneyReference?.let { bookJourRef ->
                // viewModel.loadData() // Call your ViewModel function
                viewModel.getActionUpdateLog(
                    context.urlForAcceptance() + AppConstants.GET_ACTION_UPDATE_LOG,
                    GetActionUpdateLogsParams(
                        bookRef,
                        bookJourRef
                    )
                )
            }
        }
    }
}