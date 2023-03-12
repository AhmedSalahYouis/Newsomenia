package com.asalah.newsomenia.core.data.interceptor

import com.asalah.newsomenia.core.util.constants.ApiConstants.Companion.API_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val chainBuilder = chain.request().newBuilder()
        chainBuilder.addHeader("content-type", "application/json")
        chainBuilder.addHeader("Authorization", API_KEY)
        return chain.proceed(chainBuilder.build())
    }
}