package com.ops.airportr.domain.repository

import com.ops.airportr.domain.model.login.SignInResponse

interface CoinRepository {
    suspend fun getLogin(url: String): SignInResponse
}