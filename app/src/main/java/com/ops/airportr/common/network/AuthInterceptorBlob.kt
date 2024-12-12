package com.ops.airportr.common.network

import android.content.Context
import com.ops.airportr.common.utils.SessionManager
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptorBlob(context: Context) : Interceptor {
    private val sessionManager = SessionManager(context)

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()


        requestBuilder.addHeader("sp", "racwdl&st=2024-02-09T14:00:18Z&se=2026-09-02T21:00:18Z&spr=https&sv=2022-11-02&sr=c&sig=LL%2BI2Bx4wjDv3vlLIjAHczZC8eBfpAf7fvXZNoJBLQw%3D")
        return chain.proceed(requestBuilder.build())
    }
}