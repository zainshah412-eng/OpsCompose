package com.ops.airportr.domain.use_case.storetrackingdata

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.senddevicedata.SendDeviceDataParam
import com.ops.airportr.domain.model.senddevicedata.response.SendDeviceDataResponse
import com.ops.airportr.domain.model.storetrackingdata.StoreTrackingDataParams
import com.ops.airportr.domain.model.storetrackingdata.StoreTrackingDataResponse
import com.ops.airportr.domain.repository.CCDCoinRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class StoreTrackingDataUseCase @Inject constructor(
    private val repository: CCDCoinRepository
) {
    operator fun invoke(
        url: String,
        body: StoreTrackingDataParams
    ): kotlinx.coroutines.flow.Flow<Resource<Either<StoreTrackingDataResponse, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.storeTrackingData(
                url,
                body
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }
}