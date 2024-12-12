package com.ops.airportr.data.repository

import com.google.gson.Gson
import com.ops.airportr.common.network.Either
import com.ops.airportr.data.remote.ApiService
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.login.AuthTokenResp
import com.ops.airportr.domain.model.user.UserDetails
import com.ops.airportr.domain.repository.CoinRepository
import retrofit2.HttpException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: ApiService
) : CoinRepository {
    override suspend fun authTokenApi(
        url: String,
        grant_type: String,
        username: String,
        password: String,
        clientId: String?,
        role: String?,
        appVersion: String?,
        deviceModel: String?,
        deviceUUID: String?,
        deviceSystemName: String?,
        deviceSystemVersion: String?,
        networkType: String?,
        networkSpeed: String?,
        permissionsCamera: String?,
        permissionsLocation: String?
    ): Either<AuthTokenResp, ApiError> {
        return try {
            val response = api.authTokenApi(
                url,
                grant_type,
                username,
                password,
                clientId,
                role,
                appVersion,
                deviceModel,
                deviceUUID,
                deviceSystemName,
                deviceSystemVersion,
                networkType,
                networkSpeed,
                permissionsCamera,
                permissionsLocation
            )
            Either.Success(response)
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }

    }

    override suspend fun getUserDetails(url: String): Either<UserDetails, ApiError> {
        return try {
            val response = api.getUserDetails(url)
            if (response.isSuccessful) {
                val userDetails = response.body()
                if (userDetails != null) {
                    Either.Success(userDetails)
                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(errorResponse ?: ApiError("unknown_error", response.message(), null, null))
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

}