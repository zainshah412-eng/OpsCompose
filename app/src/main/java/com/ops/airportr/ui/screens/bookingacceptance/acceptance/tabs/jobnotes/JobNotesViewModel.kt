package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobnotes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.note.BookingNoteParams
import com.ops.airportr.domain.use_case.addnote.AddNoteUseCase
import com.ops.airportr.domain.use_case.notes.BookingNotesUseCase
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobnotes.state.AddNoteState
import com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity.states.BookingNotesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class JobNotesViewModel @Inject constructor(
    private val bookingNotesUseCase: BookingNotesUseCase,
    private val addNoteUseCase: AddNoteUseCase
) : ViewModel() {
    private val _stateBookingNotes = mutableStateOf(BookingNotesState())
    val stateBookingNotes: State<BookingNotesState> = _stateBookingNotes

    private val _stateAddNotes = mutableStateOf(AddNoteState())
    val stateAddNotes: State<AddNoteState> = _stateAddNotes
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
                            _stateBookingNotes.value = BookingNotesState(response = result.data)
                        }

                        is Either.Error -> {
                            _stateBookingNotes.value =
                                BookingNotesState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }

                is Resource.Error -> {
                    _stateBookingNotes.value = BookingNotesState(error = it.message)
                }

                is Resource.Loading -> {
                    _stateBookingNotes.value = BookingNotesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun stateAddNotes(
        url: String, params: BookingNoteParams
    ) {
        addNoteUseCase(
            url, params
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _stateAddNotes.value = AddNoteState(response = result.data)
                        }

                        is Either.Error -> {
                            _stateAddNotes.value =
                                AddNoteState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }

                is Resource.Error -> {
                    _stateAddNotes.value = AddNoteState(error = it.message)
                }

                is Resource.Loading -> {
                    _stateBookingNotes.value = BookingNotesState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}