package com.ops.airportr.ui.screens.searchbooking

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.joblist.retrievejobs.params.RetrieveJobsParams
import com.ops.airportr.domain.use_case.searchbooking.GetSpecificBookingDetailUseCase
import com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.jobs.RetrieveJobsStates
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchBookingViewModel @Inject constructor(
    private val getSpecificBookingDetailUseCase: GetSpecificBookingDetailUseCase
) : ViewModel() {
    private val _state = mutableStateOf(SearchBookingState())
    val state: State<SearchBookingState> = _state

    fun searchBooking(
        url: String, bookingRef: String
    ){
        getSpecificBookingDetailUseCase(
            url,
            bookingRef
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _state.value = SearchBookingState(response = result.data)
                        }

                        is Either.Error -> {
                            _state.value = SearchBookingState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }
                is Resource.Error -> {
                    _state.value = SearchBookingState(error = it.message)
                }

                is Resource.Loading -> {
                    _state.value = SearchBookingState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}