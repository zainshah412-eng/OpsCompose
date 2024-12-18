package com.ops.airportr.domain.model.joblist.retrievejobs.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RetrieveJobsResponse(
    @SerializedName("retrieveJobs")
    var retrieveJobs: ArrayList<RetrieveJob>? = ArrayList(),
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("responseStatus")
    var responseStatus: Int? = 0,
    @SerializedName("validationErrors")
    var validationErrors: ArrayList<String> = ArrayList()
) : Parcelable