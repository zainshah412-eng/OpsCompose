package com.ops.airportr.domain.model.login.logincred

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginCred(
    var email: String? = "",
    var password: String? = "",
) : Parcelable