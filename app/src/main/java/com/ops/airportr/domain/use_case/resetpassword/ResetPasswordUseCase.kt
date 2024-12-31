package com.ops.airportr.domain.use_case.resetpassword

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.resetpassword.ResetPasswordResponse
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class ResetPasswordUseCase @Inject constructor(
    private  val repository: CoinRepository
){
    operator fun invoke( url: String, emailAddress: String): kotlinx.coroutines.flow.Flow<Resource<Either<ResetPasswordResponse, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.resetPassword(
                url,
                emailAddress
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }
}