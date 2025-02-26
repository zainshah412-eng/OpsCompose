package com.ops.airportr.data.repository

import com.google.gson.Gson
import com.ops.airportr.common.network.Either
import com.ops.airportr.data.remote.ApiService
import com.ops.airportr.data.remote.CCDApiService
import com.ops.airportr.domain.model.acceptance.UpdateAcceptanceParam
import com.ops.airportr.domain.model.acceptance.response.UpdateAcceptanceLockResponce
import com.ops.airportr.domain.model.apierror.ApiError
import com.ops.airportr.domain.model.applyactionupdate.params.ApplyActionUpdateSealParams
import com.ops.airportr.domain.model.applyactionupdate.response.ApplyActionUpdateSealResponse
import com.ops.airportr.domain.model.bookingnotes.GetBookingNotesByReference
import com.ops.airportr.domain.model.getcommunicationlog.CCDGetCommunicationLogResponse
import com.ops.airportr.domain.model.getcommunicationlog.params.CCDGetCommunicationLogParam
import com.ops.airportr.domain.model.joblist.retrievejobs.params.RetrieveJobsParams
import com.ops.airportr.domain.model.joblist.retrievejobs.response.RetrieveJobsResponse
import com.ops.airportr.domain.model.login.AuthTokenResp
import com.ops.airportr.domain.model.message.GetMessagesParam
import com.ops.airportr.domain.model.message.response.GetMessagesResponse
import com.ops.airportr.domain.model.note.BookingNoteParams
import com.ops.airportr.domain.model.note.BookingNoteResponseModel
import com.ops.airportr.domain.model.registerdevice.RegisterDeviceParams
import com.ops.airportr.domain.model.registerdevice.response.RegisterDeviceResponse
import com.ops.airportr.domain.model.resetpassword.ResetPasswordResponse
import com.ops.airportr.domain.model.searchbooking.BookingDetail
import com.ops.airportr.domain.model.senddevicedata.SendDeviceDataParam
import com.ops.airportr.domain.model.senddevicedata.response.SendDeviceDataResponse
import com.ops.airportr.domain.model.storetrackingdata.StoreTrackingDataParams
import com.ops.airportr.domain.model.storetrackingdata.StoreTrackingDataResponse
import com.ops.airportr.domain.model.updatejob.UpdateJobParam
import com.ops.airportr.domain.model.updatejob.UpdateUserResponse
import com.ops.airportr.domain.model.updatelogs.GetActionUpdateLogsResponse
import com.ops.airportr.domain.model.updatelogs.params.GetActionUpdateLogsParams
import com.ops.airportr.domain.model.user.UserDetails
import com.ops.airportr.domain.model.whatsnew.WhatsNewResponse
import com.ops.airportr.domain.repository.CCDCoinRepository
import com.ops.airportr.domain.repository.CoinRepository
import retrofit2.HttpException
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: ApiService,
    private val ccdApiService: CCDApiService
) : CoinRepository, CCDCoinRepository {
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
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }


    override suspend fun resetPassword(
        url: String,
        emailAddress: String
    ): Either<ResetPasswordResponse, ApiError> {
        return try {
            val response = api.resetPassword(url, emailAddress)
            if (response.isSuccessful) {
                val resetPassword = response.body()
                if (resetPassword != null) {
                    Either.Success(resetPassword)
                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

    override suspend fun retrieveJobs(
        url: String,
        params: RetrieveJobsParams
    ): Either<RetrieveJobsResponse, ApiError> {
        return try {
            val response = api.retrieveJobs(url, params)
            if (response.isSuccessful) {
                val retrieveJobs = response.body()
                if (retrieveJobs != null) {
                    Either.Success(retrieveJobs)
                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

    override suspend fun registerDevice(
        url: String,
        params: RegisterDeviceParams
    ): Either<RegisterDeviceResponse, ApiError> {
        return try {
            val response = api.registerDevice(url, params)
            if (response.isSuccessful) {
                val registerDeviceResponse = response.body()
                if (registerDeviceResponse != null) {
                    Either.Success(registerDeviceResponse)

                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }

    }

    override suspend fun getAppVersionsApi(
        url: String,
        populate: String
    ): Either<WhatsNewResponse, ApiError> {
        return try {
            val response = api.getAppVersionsApi(url, populate)
            if (response.isSuccessful) {
                val registerDeviceResponse = response.body()
                if (registerDeviceResponse != null) {
                    Either.Success(registerDeviceResponse)

                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

    override suspend fun getSpecificBookingDetails(
        url: String,
        bookingReference: String
    ): Either<BookingDetail, ApiError> {
        return try {
            val response = api.getSpecificBookingDetails(url, bookingReference)
            if (response.isSuccessful) {
                val resp = response.body()
                if (resp != null) {
                    Either.Success(resp)

                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

    override suspend fun getBookingNotesByBookingReference(
        url: String,
        bookingReference: String
    ): Either<GetBookingNotesByReference, ApiError> {
        return try {
            val response = api.getBookingNotesByBookingReference(url, bookingReference)
            if (response.isSuccessful) {
                val resp = response.body()
                if (resp != null) {
                    Either.Success(resp)

                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

    override suspend fun getActionUpdateLog(
        url: String,
        body: GetActionUpdateLogsParams
    ): Either<GetActionUpdateLogsResponse, ApiError> {
        return try {
            val response = api.getActionUpdateLog(url, body)
            if (response.isSuccessful) {
                val resp = response.body()
                if (resp != null) {
                    Either.Success(resp)

                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

    override suspend fun updateAcceptanceLock(
        url: String,
        body: UpdateAcceptanceParam
    ): Either<UpdateAcceptanceLockResponce, ApiError> {
        return try {
            val response = api.updateAcceptanceLock(url, body)
            if (response.isSuccessful) {
                val resp = response.body()
                if (resp != null) {
                    Either.Success(resp)

                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

    override suspend fun smsDeviceData(
        url: String,
        body: SendDeviceDataParam
    ): Either<SendDeviceDataResponse, ApiError> {
        return try {
            val response = api.smsDeviceData(url, body)
            if (response.isSuccessful) {
                val resp = response.body()
                if (resp != null) {
                    Either.Success(resp)

                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

    override suspend fun updateJob(
        url: String,
        body: UpdateJobParam
    ): Either<UpdateUserResponse, ApiError> {
        return try {
            val response = api.updateJob(url, body)
            if (response.isSuccessful) {
                val resp = response.body()
                if (resp != null) {
                    Either.Success(resp)

                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

    override suspend fun applyActionUpdateNew(
        url: String,
        body: ApplyActionUpdateSealParams
    ): Either<ApplyActionUpdateSealResponse, ApiError> {
        return try {
            val response = api.applyActionUpdateNew(url, body)
            if (response.isSuccessful) {
                val resp = response.body()
                if (resp != null) {
                    Either.Success(resp)

                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

    override suspend fun addBookingNote(
        url: String,
        body: BookingNoteParams
    ): Either<BookingNoteResponseModel, ApiError> {
        return try {
            val response = api.addBookingNote(url, body)
            if (response.isSuccessful) {
                val resp = response.body()
                if (resp != null) {
                    Either.Success(resp)

                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

    override suspend fun getMessages(
        url: String,
        body: GetMessagesParam
    ): Either<GetMessagesResponse, ApiError> {
        return try {
            val response = api.getMessages(url, body)
            if (response.isSuccessful) {
                val resp = response.body()
                if (resp != null) {
                    Either.Success(resp)

                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

    override suspend fun getCommunicationLog(
        url: String,
        body: CCDGetCommunicationLogParam
    ): Either<CCDGetCommunicationLogResponse, ApiError> {
        return try {
            val response = ccdApiService.getCommunicationLog(url, body)
            if (response.isSuccessful) {
                val resp = response.body()
                if (resp != null) {
                    Either.Success(resp)

                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
            }
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = errorBody?.let {
                Gson().fromJson(it, ApiError::class.java)
            }
            Either.Error(errorResponse ?: ApiError("unknown_error", e.message(), null, null))
        }
    }

    override suspend fun storeTrackingData(
        url: String,
        body: StoreTrackingDataParams
    ): Either<StoreTrackingDataResponse, ApiError> {
        return try {
            val response = ccdApiService.storeTrackingData(url, body)
            if (response.isSuccessful) {
                val resp = response.body()
                if (resp != null) {
                    Either.Success(resp)

                } else {
                    Either.Error(ApiError("null_body", "Response body is null", null, null))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorResponse = errorBody?.let {
                    Gson().fromJson(it, ApiError::class.java)
                }
                Either.Error(
                    errorResponse ?: ApiError(
                        "unknown_error",
                        response.message(),
                        null,
                        null
                    )
                )
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