package com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.jobs

import com.ops.airportr.domain.model.joblist.retrievejobs.response.RetrieveJobsResponse

data class RetrieveJobsStates(
    var isLoading: Boolean = false,
    var jobsResponse: RetrieveJobsResponse? = null,
    var error: String? = null
)