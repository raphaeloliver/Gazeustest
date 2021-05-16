package com.example.gazeuslibrary.remote

import android.app.Application
import com.example.gazeuslibrary.Constants.URL_BASE
import com.example.gazeuslibrary.utils.Network
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService(private val application: Application) {

    private lateinit var okHttpClient: OkHttpClient

    fun <T> create(restApiClass: Class<T>) : T {

        return Retrofit.Builder()
            .baseUrl(URL_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttp())
            .build()
            .create(restApiClass)
    }

    private fun createOkHttp() : OkHttpClient {

        val Size = (5 * 1024 * 1024).toLong()
        val request = Cache(application.cacheDir, Size)

        val okHttpBuilder = OkHttpClient.Builder()
            .cache(request)
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (Network.availableNetwork(application.applicationContext))
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7).build()
                chain.proceed(request)
            }
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(
                Interceptor()
            )

        okHttpClient = okHttpBuilder.build()

        return okHttpClient
    }
}