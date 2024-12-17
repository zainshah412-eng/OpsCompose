package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Job(
    @SerializedName("jobId")
    var jobId: String = "",
    @SerializedName("jobTemplateStepId")
    var jobTemplateStepId: String = "",
    @SerializedName("jobStatus")
    var jobStatus: Int = 0,
    @SerializedName("jobStartDateTimeUTC")
    var jobStartDateTimeUTC: String? = null,
    @SerializedName("jobEndDateTimeUTC")
    var jobEndDateTimeUTC: String? = null,
    @SerializedName("processedDurationMinutes")
    var processedDurationMinutes: Int = 0,
    @SerializedName("startDueDateTimeUTC")
    var startDueDateTimeUTC: String = "",
    @SerializedName("endDueDateTimeUTC")
    var endDueDateTimeUTC: String = "",
    @SerializedName("jobNumber")
    var jobNumber: String = "",
    @SerializedName("jobActivityStatus")
    var jobActivityStatus: Int = 0,
    @SerializedName("jobType")
    var jobType: Int = 0,
    @SerializedName("isCompulsory")
    var isCompulsory: Boolean = false,
    @SerializedName("bookingReference")
    var bookingReference: String = "",
    @SerializedName("bookingJourneyReference")
    var bookingJourneyReference: String = "",
    @SerializedName("addOnProducts")
    var addOnProducts: List<AddOnProduct> = listOf(),
    @SerializedName("referenceId")
    var referenceId: String? = null,
    @SerializedName("currentChampion")
    var currentChampion: CurrentChampion = CurrentChampion(),
    @SerializedName("completionDueDateTimeUTC")
    var completionDueDateTimeUTC: String = "",
    @SerializedName("startingLocation")
    var startingLocation: StartingLocation = StartingLocation(),
    @SerializedName("endLocation")
    var endLocation:  StartingLocation = StartingLocation(),
    @SerializedName("stepNumber")
    var stepNumber: Int = 0,
    @SerializedName("locationId")
    var locationId: String? = null,
    @SerializedName("jobNotes")
    var jobNotes: ArrayList<JobNote> = ArrayList(),
    @SerializedName("jobRelatedBags")
    var jobRelatedBags: ArrayList<JobRelatedBag> = ArrayList(),
    @SerializedName("actions")
    var actions: ArrayList<Action> = ArrayList(),
    @SerializedName("leadPassengerLite")
    var leadPassengerLite: PassengerLiteItem? = null,
    @SerializedName("isAllowedToView")
    var isAllowedToView: Boolean = false
) : Parcelable