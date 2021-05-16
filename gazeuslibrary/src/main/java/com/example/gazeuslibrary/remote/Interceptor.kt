package com.example.gazeuslibrary.remote

import com.example.gazeuslibrary.Constants.ACCEPT
import com.example.gazeuslibrary.Constants.ACCEPT_API_APPLICATION
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

class Interceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val headerRequest = request.newBuilder()
            .header(ACCEPT, ACCEPT_API_APPLICATION)
            .build()
        return chain.proceed(headerRequest)
    }
}