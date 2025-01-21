package com.ops.airportr.domain.model.getImage

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetImageParams(
    @SerializedName("height")
    var height: Int = 0,
    @SerializedName("imageId")
    var imageId: String = "",
    @SerializedName("width")
    var width: Int = 0
) : Parcelable