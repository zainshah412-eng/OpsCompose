package com.ops.airportr.domain.use_case.updatejob

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.updatejob.UpdateJobParam
import com.ops.airportr.domain.model.updatejob.UpdateUserResponse
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class UpdateJobUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(
        url: String,
        body: UpdateJobParam
    ): kotlinx.coroutines.flow.Flow<Resource<Either<UpdateUserResponse, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.updateJob(
                url,
                body
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }
}