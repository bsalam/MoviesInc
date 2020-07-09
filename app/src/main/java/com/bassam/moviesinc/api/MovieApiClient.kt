package com.bassam.moviesinc.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Bassam Hamada on 7/9/20.
 */
object MovieApiClient {
    val movieApi: MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient().newBuilder().run {
                // add log interceptor
                val logInterceptor = HttpLoggingInterceptor()
                logInterceptor.apply { logInterceptor.level = HttpLoggingInterceptor.Level.BODY }
                addInterceptor(logInterceptor)
                // add auth header interceptor
                addInterceptor(Interceptor {
                    it.run {
                        AuthHeaderInterceptor().intercept(this)
                    }
                })
                build()
            })
            .build().create(MovieApi::class.java)
    }
}