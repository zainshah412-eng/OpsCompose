package com.ops.airportr.data.remote


import com.ops.airportr.domain.model.login.SignInResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun loginApi(
        @Url url: String,
    ): SignInResponse
}