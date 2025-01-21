package com.ops.airportr.domain.model.getImage.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GetImageResponse(
    @SerializedName("imageUrl")
    var imageUrl: String = "",
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("responseStatus")
    var responseStatus: Int = 0,
    @SerializedName("validationErrors")
    var validationErrors: List<String> = listOf()
) : Parcelable