package com.ops.airportr.domain.model.whatsnew.updatenew.param

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetAppVersionParamWith(
    var url: String,
    var populate: String = "deep"
) : Parcelable