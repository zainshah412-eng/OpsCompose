package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobsms

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ops.airportr.domain.use_case.addnote.AddNoteUseCase
import com.ops.airportr.domain.use_case.message.JobMessageUseCase
import com.ops.airportr.domain.use_case.notes.BookingNotesUseCase
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobsms.state.JobMessagesState
import com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity.states.BookingNotesState
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class JobMessagesViewModel(
    private val jobMessageUseCase: JobMessageUseCase,
) : ViewModel()  {
    private val _stateJobMessage = mutableStateOf(JobMessagesState())
    val stateJobMessage: State<JobMessagesState> = _stateJobMessage

    fun getJobMessages(){

    }
}