package com.aligkts.movie.data

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ali Göktaş on 13,March,2020
 */
@Singleton
class RequestInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val httpUrl = request.url.newBuilder()
            .addQueryParameter(API_KEY_QUERY, API_KEY_VALUE)
            .build()

        request = request.newBuilder().url(httpUrl).build()

        return chain.proceed(request)
    }

    companion object {
        const val BASE_URL = "http://www.omdbapi.com/"
        const val API_KEY_QUERY = "apikey"
        const val API_KEY_VALUE = "730d1243"
    }
}