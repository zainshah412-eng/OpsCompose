package com.ops.airportr.domain.model.joblist.retrievejobs.params

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RetrieveJobsParamsWithUrl(
    var url: String,
    var retrieveJobsParams: RetrieveJobsParams
) : Parcelable
