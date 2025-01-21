package com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.acceptance.UpdateAcceptanceParam
import com.ops.airportr.domain.model.senddevicedata.SendDeviceDataParam
import com.ops.airportr.domain.model.updatejob.UpdateJobParam
import com.ops.airportr.domain.model.updatelogs.params.GetActionUpdateLogsParams
import com.ops.airportr.domain.use_case.acceptance.UpdateAcceptanceLockUseCase
import com.ops.airportr.domain.use_case.actionupdateslogs.GetActionUpdateLogsUseCase
import com.ops.airportr.domain.use_case.senddevicedata.SmsDeviceDataUseCase
import com.ops.airportr.domain.use_case.updatejob.UpdateJobUseCase
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.states.SmsDeviceDataState
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.states.UpdateAcceptanceLockState
import com.ops.airportr.ui.screens.bookingacceptance.acceptance.tabs.jobdetails.states.UpdateJobState
import com.ops.airportr.ui.screens.bookingmanifest.tabs.bookingactivity.states.GetActionUpdateLogsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class JobDetailsScreenViewModel @Inject constructor(
    private val updateAcceptanceLockUseCase: UpdateAcceptanceLockUseCase,
    private val smsDeviceDataUseCase: SmsDeviceDataUseCase,
    private val updateJobUseCase: UpdateJobUseCase,
    private val getActionUpdateLogsUseCase: GetActionUpdateLogsUseCase

) : ViewModel() {
    private val _stateAcceptance = mutableStateOf(UpdateAcceptanceLockState())
    val stateAcceptance: State<UpdateAcceptanceLockState> = _stateAcceptance


    private val _stateSmsDeviceData = mutableStateOf(SmsDeviceDataState())
    val smsDeviceData: State<SmsDeviceDataState> = _stateSmsDeviceData


    private val _stateUpdateJob = mutableStateOf(UpdateJobState())
    val updateJob: State<UpdateJobState> = _stateUpdateJob

    private val _stateActonUpdateLogs = mutableStateOf(GetActionUpdateLogsState())
    val stateActonUpdateLogs: State<GetActionUpdateLogsState> = _stateActonUpdateLogs

    fun updateAcceptanceLock(
        url: String,
        updateAcceptanceParam: UpdateAcceptanceParam
    ) {
        updateAcceptanceLockUseCase(
            url,
            updateAcceptanceParam
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _stateAcceptance.value =
                                UpdateAcceptanceLockState(response = result.data)
                        }

                        is Either.Error -> {
                            _stateAcceptance.value =
                                UpdateAcceptanceLockState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }

                is Resource.Error -> {
                    _stateAcceptance.value = UpdateAcceptanceLockState(error = it.message)
                }

                is Resource.Loading -> {
                    _stateAcceptance.value = UpdateAcceptanceLockState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)

    }

    fun smsDeviceData(
        url: String,
        body: SendDeviceDataParam
    ) {
        smsDeviceDataUseCase(
            url, body
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _stateSmsDeviceData.value = SmsDeviceDataState(response = result.data)
                        }

                        is Either.Error -> {
                            _stateSmsDeviceData.value =
                                SmsDeviceDataState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }

                is Resource.Error -> {
                    _stateSmsDeviceData.value = SmsDeviceDataState(error = it.message)
                }

                is Resource.Loading -> {
                    _stateSmsDeviceData.value = SmsDeviceDataState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun updateJob(
        url: String,
        updateJobParam: UpdateJobParam
    ) {
        updateJobUseCase(
            url,
            updateJobParam
        ).onEach {

            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _stateUpdateJob.value = UpdateJobState(response = result.data)
                        }

                        is Either.Error -> {
                            _stateUpdateJob.value =
                                UpdateJobState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }

                is Resource.Error -> {
                    _stateUpdateJob.value = UpdateJobState(error = it.message)
                }

                is Resource.Loading -> {
                    _stateUpdateJob.value = UpdateJobState(isLoading = true)
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





