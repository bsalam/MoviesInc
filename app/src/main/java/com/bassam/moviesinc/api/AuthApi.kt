package com.bassam.moviesinc.api

import com.bassam.moviesinc.api.data.*
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface AuthApi {

    // request token
    @POST("4/auth/request_token")
    fun requestToken(): Observable<RequestTokenRes>

    // access token
    @POST("4/auth/access_token")
    fun accessToken(@Body body: AccessTokenBody): Observable<AccessTokenRes>

    // session
    @POST("3/authentication/session/convert/4")
    fun session(@Body body: SessionBody): Observable<SessionRes>

    // Service access
    companion object {

        private const val CONTENT_TYPE = "application/json;charset=utf-8"
        private const val AUTH =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmMGM1YzZhOGUyNzA4NWU3MDViODFmODAxNDMzMmY4NSIsInN1YiI6IjVmMDExMjZjYTI4NGViMDAzOGEwZTMwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.BCYDodK6_34PK_UzZfz1YkykE-MU-6QJuW4d7E9tFFw"

        fun create(): AuthApi {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.themoviedb.org/")

                // add interceptor
                .client(OkHttpClient().newBuilder().run {
                    addInterceptor(Interceptor {
                        it.run {
                            proceed(request().newBuilder().run {
                                addHeader("Content-Type", CONTENT_TYPE)
                                addHeader("Authorization", AUTH)
                                build()
                            })
                        }
                    })
                    build()
                })
                .build()

            return retrofit.create(AuthApi::class.java)
        }
    }
}