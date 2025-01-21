package com.ops.airportr.domain.model.getcommunicationlog

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CustomActionUpdateModel(
    var name: String?,
    var message: String? = "",
    var dateTimeUTC: String = "",
    var actionType: Int = 0,
    var actionCreatorRole: Int = 0,
    var scanType: Int? = -1, //1 for IATA , 2 for Seal
    var bookingReference: String? = "",
    var amountPaid: String? = "",
    val from: String = "",

    ) : Parcelable
