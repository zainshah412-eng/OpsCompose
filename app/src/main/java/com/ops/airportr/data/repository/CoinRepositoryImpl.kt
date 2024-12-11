package com.ops.airportr.data.repository

import com.ops.airportr.data.remote.ApiService
import com.ops.airportr.domain.model.login.SignInResponse
import com.ops.airportr.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: ApiService
) : CoinRepository {
    override suspend fun getLogin(url:String): SignInResponse {
        return api.loginApi(url)
    }
}