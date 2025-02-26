package com.ops.airportr.domain.use_case.addnote

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.note.BookingNoteParams
import com.ops.airportr.domain.model.note.BookingNoteResponseModel
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(
        url: String,
        params: BookingNoteParams
    ): kotlinx.coroutines.flow.Flow<Resource<Either<BookingNoteResponseModel, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.addBookingNote(
                url,
                params
            )
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }
}