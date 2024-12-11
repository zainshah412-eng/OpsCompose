package com.ops.airportr.domain.use_case.login

import android.util.Log
import com.ops.airportr.common.Resource
import com.ops.airportr.domain.model.login.SignInResponse
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLoginUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(url:String): Flow<Resource<SignInResponse>> = flow {
        try {
            emit(Resource.Loading<SignInResponse>())
            val coin = repository.getLogin(url)
            Log.d("DATA",coin.toString())
            emit(Resource.Success<SignInResponse>(coin))
        } catch(e: HttpException) {
            emit(Resource.Error<SignInResponse>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<SignInResponse>("Couldn't reach server. Check your internet connection."))
        }
    }

}