package com.ops.airportr.domain.use_case.message

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.message.GetMessagesParam
import com.ops.airportr.domain.model.message.response.GetMessagesResponse
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class JobMessageUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(
        url: String,
        body: GetMessagesParam
    ): kotlinx.coroutines.flow.Flow<Resource<Either<GetMessagesResponse, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getMessages(
                url,
                body
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }

}