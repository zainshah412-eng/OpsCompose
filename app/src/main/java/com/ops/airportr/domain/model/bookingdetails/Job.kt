package com.ops.airportr.domain.model.bookingdetails

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Job(
    @SerializedName("jobId")
    var jobId: String? = "",
    @SerializedName("jobTemplateStepId")
    var jobTemplateStepId: String? = "",
    @SerializedName("jobStatus")
    var jobStatus: Int? = 0,
    @SerializedName("jobStartDateTimeUTC")
    var jobStartDateTimeUTC: String? = "",
    @SerializedName("jobEndDateTimeUTC")
    var jobEndDateTimeUTC: String? = "",
    @SerializedName("processedDurationMinutes")
    var processedDurationMinutes: Int? = 0,
    @SerializedName("startDueDateTimeUTC")
    var startDueDateTimeUTC: String? = "",
    @SerializedName("endDueDateTimeUTC")
    var endDueDateTimeUTC: String? = "",
    @SerializedName("jobNumber")
    var jobNumber: String? = "",
    @SerializedName("jobActivityStatus")
    var jobActivityStatus: Int? = 0,
    @SerializedName("jobType")
    var jobType: Int? = 0,
    @SerializedName("isCompulsory")
    var isCompulsory: Boolean? = false,
    @SerializedName("bookingReference")
    var bookingReference: String? = "",
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String? = "",
    @SerializedName("addOnProducts")
    var addOnProducts: ArrayList<AddOnProduct> ?= ArrayList(),
    @SerializedName("referenceId")
    var referenceId: String? = "",
    @SerializedName("currentChampion")
    var currentChampion: CurrentChampion? = CurrentChampion(),
    @SerializedName("completionDueDateTimeUTC")
    var completionDueDateTimeUTC: String? = "",
    @SerializedName("startingLocation")
    var startingLocation: StartingLocation? = StartingLocation(),
    @SerializedName("endLocation")
    var endLocation: EndLocationBookingDetail? = EndLocationBookingDetail(),
    @SerializedName("stepNumber")
    var stepNumber: Int? = 0,
    @SerializedName("locationId")
    var locationId: String? = "",
    @SerializedName("jobNotes")
    var jobNotes: ArrayList<String>? = ArrayList(),
    @SerializedName("jobRelatedBags")
    var jobRelatedBags: ArrayList<JobRelatedBag>? = ArrayList(),
    @SerializedName("actions")
    var actions: ArrayList<Action>? = ArrayList(),
    @SerializedName("leadPassengerLite")
    var leadPassengerLite: String? = "",
    @SerializedName("isAllowedToView")
    var isAllowedToView: Boolean? = false,
    @SerializedName("bookingJourneyLinkedCitySprintJobsLite")
    var bookingJourneyLinkedCitySprintJobsLite: String? = "",
    @SerializedName("bookingJourneyLinkedLogisticsJobsLite")
    var bookingJourneyLinkedLogisticsJobsLite: BookingJourneyLinkedLogisticsJobsLite? = BookingJourneyLinkedLogisticsJobsLite()
) : Parcelable