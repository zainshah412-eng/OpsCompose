package com.ops.airportr.ui.screens.profile.whatsnew

import com.ops.airportr.domain.model.login.AuthTokenResp
import com.ops.airportr.domain.model.whatsnew.WhatsNewResponse

data class WhatsNewState(
    var isLoading: Boolean = false,
    var response: WhatsNewResponse? = null,
    var error: String? = null
)