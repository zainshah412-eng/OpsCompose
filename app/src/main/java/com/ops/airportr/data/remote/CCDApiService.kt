package com.ops.airportr.data.remote

import com.ops.airportr.domain.model.getcommunicationlog.CCDGetCommunicationLogResponse
import com.ops.airportr.domain.model.getcommunicationlog.params.CCDGetCommunicationLogParam
import com.ops.airportr.domain.model.storetrackingdata.StoreTrackingDataParams
import com.ops.airportr.domain.model.storetrackingdata.StoreTrackingDataResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Url

interface CCDApiService {
    @POST
    suspend fun getCommunicationLog(
        @Url url: String,
        @Body body: CCDGetCommunicationLogParam,
    ): Response<CCDGetCommunicationLogResponse>

    @POST
    suspend fun storeTrackingData(
        @Url url: String,
        @Body body: StoreTrackingDataParams,
    ): Response<StoreTrackingDataResponse>

}