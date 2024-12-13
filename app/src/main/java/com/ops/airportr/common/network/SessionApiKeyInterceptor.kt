package com.ops.airportr.common.network

import android.content.Context
import com.ops.airportr.AppApplication
import com.ops.airportr.common.utils.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class SessionApiKeyInterceptor(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        AppApplication.sessionManager.subscriptionKey?.let {
            requestBuilder.addHeader(
                "Ocp-Apim-Subscription-Key",
                it
            )
        }
        return chain.proceed(requestBuilder.build())
    }
}