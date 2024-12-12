package com.ops.airportr.domain.model.apierror

import com.google.gson.annotations.SerializedName
import org.json.JSONArray

data class ApiError(
    @SerializedName("error")
    val error: String?,
    @SerializedName("error_description")
    val errorDescription: String?,
    @SerializedName("validationErrorMessages")
    val validationErrorMessages: List<ValidationErrorMessage>?,
    @SerializedName("translationResponse")
    val translationResponse: TranslationResponse?
)

data class ValidationErrorMessage(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("reasons")
    val reasons: List<String>?
)

data class TranslationResponse(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("translationValidationErrors")
    val translationValidationErrors: List<TranslationValidationError>?
)

data class TranslationValidationError(
    val errorMessage: String,
    val parameters: Parameters
)

data class Parameters(
    val remainingAttempts: String
)