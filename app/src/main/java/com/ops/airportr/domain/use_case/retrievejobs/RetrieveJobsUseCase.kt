package com.ops.airportr.domain.use_case.retrievejobs

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.joblist.retrievejobs.params.RetrieveJobsParams
import com.ops.airportr.domain.model.joblist.retrievejobs.response.RetrieveJobsResponse
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class RetrieveJobsUseCase  @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(
        url: String, params: RetrieveJobsParams
    ): Flow<Resource<Either<RetrieveJobsResponse, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.retrieveJobs(
                url,
                params
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }


}