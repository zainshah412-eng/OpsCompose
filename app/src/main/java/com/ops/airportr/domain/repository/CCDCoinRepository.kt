package com.ops.airportr.domain.repository

import com.ops.airportr.common.network.Either
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.getcommunicationlog.CCDGetCommunicationLogResponse
import com.ops.airportr.domain.model.getcommunicationlog.params.CCDGetCommunicationLogParam
import com.ops.airportr.domain.model.storetrackingdata.StoreTrackingDataParams
import com.ops.airportr.domain.model.storetrackingdata.StoreTrackingDataResponse

interface CCDCoinRepository {
    suspend fun getCommunicationLog(url: String, body: CCDGetCommunicationLogParam): Either<CCDGetCommunicationLogResponse, ApiError>
    suspend fun storeTrackingData(url: String, body: StoreTrackingDataParams): Either<StoreTrackingDataResponse, ApiError>

}