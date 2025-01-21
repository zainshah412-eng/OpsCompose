package com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.getcommunicationlog.params.CCDGetCommunicationLogParam
import com.ops.airportr.domain.model.updatelogs.params.GetActionUpdateLogsParams
import com.ops.airportr.domain.use_case.actionupdateslogs.GetActionUpdateLogsUseCase
import com.ops.airportr.domain.use_case.communicationlogs.CommunicationLogsUseCase
import com.ops.airportr.domain.use_case.notes.BookingNotesUseCase
import com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity.states.BookingNotesState
import com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity.states.GetActionUpdateLogsState
import com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity.states.GetCommunicationLogState
import com.ops.airportr.ui.screens.searchbooking.SearchBookingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class BookingActivityViewModel @Inject constructor(
    private val bookingNotesUseCase: BookingNotesUseCase,
    private val communicationLogsUseCase: CommunicationLogsUseCase,
    private val getActionUpdateLogsUseCase: GetActionUpdateLogsUseCase
) : ViewModel() {
    private val _state = mutableStateOf(BookingNotesState())
    val state: State<BookingNotesState> = _state


    private val _stateCommunicationLogs = mutableStateOf(GetCommunicationLogState())
    val stateCommunicationLogs: State<GetCommunicationLogState> = _stateCommunicationLogs


    private val _stateActonUpdateLogs = mutableStateOf(GetActionUpdateLogsState())
    val stateActonUpdateLogs: State<GetActionUpdateLogsState> = _stateActonUpdateLogs

    fun getBookingNotes(
        url: String, bookingRef: String
    ) {
        bookingNotesUseCase(
            url, bookingRef
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _state.value = BookingNotesState(response = result.data)
                        }

                        is Either.Error -> {
                            _state.value = BookingNotesState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }

                is Resource.Error -> {
                    _state.value = BookingNotesState(error = it.message)
                }

                is Resource.Loading -> {
                    _state.value = BookingNotesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getCommunicationLog(
        url: String,
        body: CCDGetCommunicationLogParam
    ) {
        communicationLogsUseCase(
            url,
            body
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _stateCommunicationLogs.value =
                                GetCommunicationLogState(response = result.data)
                        }

                        is Either.Error -> {
                            _stateCommunicationLogs.value =
                                GetCommunicationLogState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }

                is Resource.Error -> {
                    _stateCommunicationLogs.value = GetCommunicationLogState(error = it.message)
                }

                is Resource.Loading -> {
                    _stateCommunicationLogs.value = GetCommunicationLogState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getActionUpdateLog(
        url: String,
        body: GetActionUpdateLogsParams
    ) {
        getActionUpdateLogsUseCase(
            url,
            body
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _stateActonUpdateLogs.value =
                                GetActionUpdateLogsState(response = result.data)
                        }

                        is Either.Error -> {
                            _stateActonUpdateLogs.value =
                                GetActionUpdateLogsState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }

                is Resource.Error -> {
                    _stateActonUpdateLogs.value = GetActionUpdateLogsState(error = it.message)
                }

                is Resource.Loading -> {
                    _stateActonUpdateLogs.value = GetActionUpdateLogsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

}