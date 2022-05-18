package com.bacteria.bestfilm.data.remote

import com.bacteria.bestfilm.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .header("api_key", BuildConfig.API_KEY)
            .build()
        return chain.proceed(request)
    }

}