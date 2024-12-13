package com.ops.airportr.domain.use_case.login

import com.ops.airportr.common.network.Either
import com.ops.airportr.common.network.Resource
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.login.AuthTokenResp
import com.ops.airportr.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetAuthUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(
        url: String, grant_type: String,
        username: String, password: String,
        clientId: String?, role: String?,
        appVersion: String?, deviceModel: String?,
        deviceUUID: String?, deviceSystemName: String?,
        deviceSystemVersion: String?, networkType: String?,
        networkSpeed: String?, permissionsCamera: String?,
        permissionsLocation: String?
    ): Flow<Resource<Either<AuthTokenResp, ApiError>>> = flow {
        try {
            emit(Resource.Loading())
            val result = repository.authTokenApi(
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
            emit(Resource.Success(result))
        } catch (e: IOException) {
            emit(Resource.Error("Internet Connection Error"))
        }
    }


}