package com.ops.airportr.domain.use_case.actionupdateslogs

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.updatelogs.GetActionUpdateLogsResponse
import com.ops.airportr.domain.model.updatelogs.params.GetActionUpdateLogsParams
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetActionUpdateLogsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(
        url: String,
        body: GetActionUpdateLogsParams
    ): kotlinx.coroutines.flow.Flow<Resource<Either<GetActionUpdateLogsResponse, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getActionUpdateLog(
                url,
                body
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }
}