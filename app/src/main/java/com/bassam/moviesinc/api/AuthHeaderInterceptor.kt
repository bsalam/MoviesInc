package com.bassam.moviesinc.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Bassam Hamada on 7/9/20.
 */
class AuthHeaderInterceptor() : Interceptor {

    companion object {
        private const val KEY_AUTHORIZATION = "Authorization"
        private const val KEY_CONTENT_TYPE = "Authorization"
        private const val BEARER = "Bearer "
        private const val CONTENT_TYPE = "application/json;charset=utf-8"
        private const val TOKEN =
            "${BEARER}eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmMGM1YzZhOGUyNzA4NWU3MDViODFmODAxNDMzMmY4NSIsInN1YiI6IjVmMDExMjZjYTI4NGViMDAzOGEwZTMwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.BCYDodK6_34PK_UzZfz1YkykE-MU-6QJuW4d7E9tFFw"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder().run {
            addHeader(KEY_CONTENT_TYPE, CONTENT_TYPE)
            addHeader(KEY_AUTHORIZATION, TOKEN)
        }
        return chain.proceed(builder.build())
    }
}
