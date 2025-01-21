package com.ops.airportr.domain.use_case.acceptance

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.acceptance.UpdateAcceptanceParam
import com.ops.airportr.domain.model.acceptance.response.UpdateAcceptanceLockResponce
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.updatelogs.GetActionUpdateLogsResponse
import com.ops.airportr.domain.model.updatelogs.params.GetActionUpdateLogsParams
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateAcceptanceLockUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(
        url: String,
        body: UpdateAcceptanceParam
    ): kotlinx.coroutines.flow.Flow<Resource<Either<UpdateAcceptanceLockResponce, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.updateAcceptanceLock(
                url,
                body
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }
}