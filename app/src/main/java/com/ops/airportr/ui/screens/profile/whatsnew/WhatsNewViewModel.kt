package com.ops.airportr.ui.screens.profile.whatsnew

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.use_case.whatsnew.WhatsNewUseCase
import com.ops.airportr.ui.screens.loginscreen.states.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WhatsNewViewModel @Inject constructor(
    private val whatsNewUseCase: WhatsNewUseCase
) : ViewModel() {
    private val _state = mutableStateOf(WhatsNewState())
    val state: State<WhatsNewState> = _state

    fun whatNewApi(
        url: String,
        populate: String
    ) {
        whatsNewUseCase(
            url,
            populate
        ).onEach {
            when (it) {
                is Resource.Success -> {
                    when (val result = it.data) {
                        is Either.Success -> {
                            _state.value = WhatsNewState(response = result.data)
                        }

                        is Either.Error -> {
                            _state.value = WhatsNewState(error = result.error.errorDescription)
                        }

                        else -> {

                        }
                    }
                }

                is Resource.Error -> {
                    _state.value = WhatsNewState(error = it.message)
                }

                is Resource.Loading -> {
                    _state.value = WhatsNewState(isLoading = true)
                }
            }
        }
    }
}