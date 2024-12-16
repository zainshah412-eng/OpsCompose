package com.ops.airportr.domain.repository

import com.ops.airportr.common.network.Either
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.login.AuthTokenResp
import com.ops.airportr.domain.model.resetpassword.ResetPasswordParam
import com.ops.airportr.domain.model.resetpassword.ResetPasswordResponse
import com.ops.airportr.domain.model.user.UserDetails

interface CoinRepository {
    suspend fun authTokenApi(url: String, grant_type: String,
                             username: String, password: String,
                             clientId: String?, role: String?,
                             appVersion: String?, deviceModel: String?,
                             deviceUUID: String?, deviceSystemName: String?,
                             deviceSystemVersion: String?, networkType: String?,
                             networkSpeed: String?, permissionsCamera: String?,
                             permissionsLocation: String?): Either<AuthTokenResp, ApiError>

    suspend fun getUserDetails(url: String): Either<UserDetails, ApiError>

    suspend fun resetPassword(url: String, emailAddress: String): Either<ResetPasswordResponse,ApiError>
}