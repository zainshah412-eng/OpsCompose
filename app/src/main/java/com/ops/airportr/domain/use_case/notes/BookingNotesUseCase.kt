package com.ops.airportr.domain.use_case.notes

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.bookingnotes.GetBookingNotesByReference
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class BookingNotesUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(
        url: String,
        bookingRef: String
    ): kotlinx.coroutines.flow.Flow<Resource<Either<GetBookingNotesByReference, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.getBookingNotesByBookingReference(
                url,
                bookingRef
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }
}