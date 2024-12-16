package com.ops.airportr.data.remote


import com.ops.airportr.domain.model.login.AuthTokenResp
import com.ops.airportr.domain.model.resetpassword.ResetPasswordParam
import com.ops.airportr.domain.model.resetpassword.ResetPasswordResponse
import com.ops.airportr.domain.model.user.UserDetails
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
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
}