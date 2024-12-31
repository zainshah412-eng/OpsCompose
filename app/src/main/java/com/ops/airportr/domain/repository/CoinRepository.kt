package com.ops.airportr.domain.repository

import com.ops.airportr.common.network.Either
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.joblist.retrievejobs.params.RetrieveJobsParams
import com.ops.airportr.domain.model.joblist.retrievejobs.response.RetrieveJobsResponse
import com.ops.airportr.domain.model.login.AuthTokenResp

import com.ops.airportr.domain.model.resetpassword.ResetPasswordParam
import com.ops.airportr.domain.model.resetpassword.ResetPasswordResponse

import com.ops.airportr.domain.model.registerdevice.RegisterDeviceParams
import com.ops.airportr.domain.model.registerdevice.response.RegisterDeviceResponse
import com.ops.airportr.domain.model.searchbooking.BookingDetail

import com.ops.airportr.domain.model.user.UserDetails
import com.ops.airportr.domain.model.whatsnew.WhatsNewResponse

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
    suspend fun retrieveJobs(url: String, params: RetrieveJobsParams): Either<RetrieveJobsResponse, ApiError>
    suspend fun registerDevice(url: String, params: RegisterDeviceParams): Either<RegisterDeviceResponse, ApiError>
    suspend fun getAppVersionsApi(url: String, populate:String): Either<WhatsNewResponse, ApiError>
    suspend fun getSpecificBookingDetails(url: String, bookingReference:String): Either<BookingDetail, ApiError>

}