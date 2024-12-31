package com.ops.airportr.domain.use_case.searchbooking

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.searchbooking.BookingDetail
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetSpecificBookingDetailUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(
        url: String,
        bookingRef: String
    ): kotlinx.coroutines.flow.Flow<Resource<Either<BookingDetail, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getSpecificBookingDetails(
                url,
                bookingRef
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }
}