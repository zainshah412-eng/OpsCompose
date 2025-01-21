package com.ops.airportr.data.remote


import com.ops.airportr.domain.model.acceptance.UpdateAcceptanceParam
import com.ops.airportr.domain.model.acceptance.response.UpdateAcceptanceLockResponce
import com.ops.airportr.domain.model.bookingnotes.GetBookingNotesByReference
import com.ops.airportr.domain.model.getImage.GetImageParams
import com.ops.airportr.domain.model.getImage.response.GetImageResponse
import com.ops.airportr.domain.model.joblist.retrievejobs.params.RetrieveJobsParams
import com.ops.airportr.domain.model.joblist.retrievejobs.response.RetrieveJobsResponse
import com.ops.airportr.domain.model.login.AuthTokenResp
import com.ops.airportr.domain.model.resetpassword.ResetPasswordParam
import com.ops.airportr.domain.model.resetpassword.ResetPasswordResponse
import com.ops.airportr.domain.model.registerdevice.RegisterDeviceParams
import com.ops.airportr.domain.model.registerdevice.response.RegisterDeviceResponse
import com.ops.airportr.domain.model.searchbooking.BookingDetail
import com.ops.airportr.domain.model.senddevicedata.SendDeviceDataParam
import com.ops.airportr.domain.model.senddevicedata.response.SendDeviceDataResponse
import com.ops.airportr.domain.model.updatejob.UpdateJobParam
import com.ops.airportr.domain.model.updatejob.UpdateUserResponse
import com.ops.airportr.domain.model.updatelogs.GetActionUpdateLogsResponse
import com.ops.airportr.domain.model.updatelogs.params.GetActionUpdateLogsParams
import com.ops.airportr.domain.model.user.UserDetails
import com.ops.airportr.domain.model.whatsnew.WhatsNewResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @POST
    @FormUrlEncoded
    suspend fun authTokenApi(
        @Url url: String,
        @Field("grant_type") grant_type: String,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("clientId") clientId: String?,
        @Field("role") role: String?,
        @Field("appVersion") appVersion: String?,
        @Field("deviceModel") deviceModel: String?,
        @Field("deviceUUID") deviceUUID: String?,
        @Field("deviceSystemName") deviceSystemName: String?,
        @Field("deviceSystemVersion") deviceSystemVersion: String?,
        @Field("networkType") networkType: String?,
        @Field("networkSpeed") networkSpeed: String?,
        @Field("permissionsCamera") permissionsCamera: String?,
        @Field("permissionsLocation") permissionsLocation: String?,
    ): AuthTokenResp

    @GET
    suspend fun getUserDetails(
        @Url url: String
    ): Response<UserDetails>

    @POST
    @FormUrlEncoded
    suspend fun resetPassword(
        @Url url: String,
        @Field("emailAddress") emailAddress: String,
    ): Response<ResetPasswordResponse>
   
    @POST
    suspend fun retrieveJobs(
        @Url url: String,
        @Body body: RetrieveJobsParams,
    ): Response<RetrieveJobsResponse>

    @POST
    suspend fun registerDevice(
        @Url url: String,
        @Body registerDeviceParams: RegisterDeviceParams,
    ): Response<RegisterDeviceResponse>

    @GET
    suspend fun getAppVersionsApi(
        @Url url: String,
        @Query("populate") populate: String,
    ): Response<WhatsNewResponse>

    @GET
    suspend fun getSpecificBookingDetails(
        @Url url: String,
        @Query("bookingReference") bookingReference: String,
    ): Response<BookingDetail>

    @GET
    suspend fun getBookingNotesByBookingReference(
        @Url url: String,
        @Query("bookingReference") bookingReference: String,
    ): Response<GetBookingNotesByReference>

    @POST
    suspend fun getActionUpdateLog(
        @Url url: String,
        @Body body: GetActionUpdateLogsParams,
    ): Response<GetActionUpdateLogsResponse>

    @POST
    suspend fun getImage(
        @Url url: String,
        @Body body: GetImageParams,
    ): Response<GetImageResponse>

    @POST
    suspend fun updateAcceptanceLock(
        @Url url: String,
        @Body updateAcceptanceParam: UpdateAcceptanceParam,
    ): Response<UpdateAcceptanceLockResponce>

    @POST
    suspend fun smsDeviceData(
        @Url url: String,
        @Body body: SendDeviceDataParam,
    ): Response<SendDeviceDataResponse>
    @POST
    suspend fun updateJob(
        @Url url: String,
        @Body body: UpdateJobParam,
    ): Response<UpdateUserResponse>

}