package com.ops.airportr.domain.use_case.login

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.login.AuthTokenResp
import com.ops.airportr.domain.model.user.UserDetails
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetUserDetailUseCase  @Inject constructor(
    private val repository: CoinRepository
){
    operator fun invoke( url: String): Flow<Resource<Either<UserDetails, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getUserDetails(
                url
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }

}