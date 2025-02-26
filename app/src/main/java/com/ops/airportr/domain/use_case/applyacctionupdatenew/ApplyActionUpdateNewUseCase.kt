package com.ops.airportr.domain.use_case.applyacctionupdatenew

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.applyactionupdate.params.ApplyActionUpdateSealParams
import com.ops.airportr.domain.model.applyactionupdate.response.ApplyActionUpdateSealResponse
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class ApplyActionUpdateNewUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(
        url: String,
        body: ApplyActionUpdateSealParams
    ): kotlinx.coroutines.flow.Flow<Resource<Either<ApplyActionUpdateSealResponse, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.applyActionUpdateNew(
                url,
                body
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }
}