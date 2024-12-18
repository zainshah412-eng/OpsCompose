package com.ops.airportr.ui.screens.navigationscreen.bottomnav.bottom.jobs

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.joblist.retrievejobs.params.RetrieveJobsParams
import com.ops.airportr.domain.use_case.retrievejobs.RetrieveJobsUseCase
import com.ops.airportr.ui.screens.loginscreen.states.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RetrieveJobsViewModel  @Inject constructor(
    private val retrieveJobsUseCase: RetrieveJobsUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(RetrieveJobsStates())
    val state: State<RetrieveJobsStates> = _state

    fun retrieveJobs(
        url: String, params: RetrieveJobsParams
    ){
        retrieveJobsUseCase(
            url,
            params
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _state.value = RetrieveJobsStates(jobsResponse = result.data)
                        }

                        is Either.Error -> {
                            _state.value = RetrieveJobsStates(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }
                is Resource.Error -> {
                    _state.value = RetrieveJobsStates(error = it.message)
                }

                is Resource.Loading -> {
                    _state.value = RetrieveJobsStates(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}