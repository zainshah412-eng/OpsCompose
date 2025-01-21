package com.ops.airportr.domain.use_case.communicationlogs

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.getcommunicationlog.CCDGetCommunicationLogResponse
import com.ops.airportr.domain.model.getcommunicationlog.params.CCDGetCommunicationLogParam
import com.ops.airportr.domain.repository.CCDCoinRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class CommunicationLogsUseCase @Inject constructor(
    private val repository: CCDCoinRepository
){
    operator fun invoke(
        url: String,
        body: CCDGetCommunicationLogParam
    ): kotlinx.coroutines.flow.Flow<Resource<Either<CCDGetCommunicationLogResponse, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getCommunicationLog(
                url,
                body
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }
}