package com.ops.airportr.domain.use_case

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.registerdevice.RegisterDeviceParams
import com.ops.airportr.domain.model.registerdevice.response.RegisterDeviceResponse
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class RegisterDeviceUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(
        url: String, params: RegisterDeviceParams
    ): Flow<Resource<Either<RegisterDeviceResponse, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.registerDevice(
                url,
                params
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }


}