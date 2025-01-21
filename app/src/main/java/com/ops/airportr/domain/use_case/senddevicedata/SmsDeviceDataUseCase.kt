package com.ops.airportr.domain.use_case.senddevicedata

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.acceptance.UpdateAcceptanceParam
import com.ops.airportr.domain.model.acceptance.response.UpdateAcceptanceLockResponce
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.senddevicedata.SendDeviceDataParam
import com.ops.airportr.domain.model.senddevicedata.response.SendDeviceDataResponse
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class SmsDeviceDataUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(
        url: String,
        body: SendDeviceDataParam
    ): kotlinx.coroutines.flow.Flow<Resource<Either<SendDeviceDataResponse, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.smsDeviceData(
                url,
                body
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }
}