package com.ops.airportr.common.utils.callbacks

import com.ops.airportr.AppApplication
import com.ops.airportr.common.AppConstants
import com.ops.airportr.common.network.AuthApiKeyInterceptor
import com.ops.airportr.common.network.AuthInterceptor
import com.ops.airportr.common.network.NetworkInterceptor
import com.ops.airportr.common.network.SessionApiKeyInterceptor
import com.ops.airportr.common.utils.extension.urlForAcceptance
import com.ops.airportr.data.remote.ApiService
import com.ops.airportr.domain.model.getImage.GetImageParams
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

class ImageLoadCallbacks {

    fun loadImageUrl(imageId: String, callback: (String?) -> Unit) {
        val model = GetImageParams(0, imageId, 0)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(AppApplication.instance.applicationContext))
            .addInterceptor(NetworkInterceptor(AppApplication.instance.applicationContext))
            .addInterceptor(AuthApiKeyInterceptor(AppApplication.instance.applicationContext))
            .addInterceptor(SessionApiKeyInterceptor(AppApplication.instance.applicationContext))
            .hostnameVerifier { _, _ -> true }
            .readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.PRODUCTION_URL_DEV)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = apiService.getImage(
                    AppApplication.instance.applicationContext.urlForAcceptance() + AppConstants.GET_IMAGE,
                    model
                )
                if (response.isSuccessful) {
                    val posts = response.body()
                    val imageUrl = posts?.imageUrl
                    withContext(Dispatchers.Main) {
                        callback(imageUrl) // Return the image URL
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        callback(null) // Handle error
                    }
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    callback(null) // Handle exception
                }
            }
        }
    }
}




