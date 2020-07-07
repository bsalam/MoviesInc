package com.bassam.moviesinc.api

import com.bassam.moviesinc.api.data.*
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MovieApi {

    // now playing
    @GET("3/movie/now_playing")
    fun nowPlaying(
        @Query("language") lang: String = DEF_LANG,
        @Query("page") action: Int = DEF_PAGE
    ): Observable<NowPlayingRes>

    // details
    @GET("3/movie/{movie_id}")
    fun details(
        @Path("movie_id", encoded = true) movieId: String,
        @Query("language") lang: String = DEF_LANG
    ): Observable<DetailsRes>

    // recommendation
    @GET("3/movie/{movie_id}/recommendations")
    fun recommendations(
        @Path("movie_id", encoded = true) movieId: String,
        @Query("language") lang: String = DEF_LANG,
        @Query("page") action: Int = DEF_PAGE
    ): Observable<RecommendationsRes>

    // rate
    @POST("3/movie/{movie_id}/rating")
    fun rate(
        @Path("movie_id", encoded = true) movieId: String,
        @Query("session_id") sessionId: String,
        @Body body: RateBody
    ): Observable<RateRes>

    // mark fav
    @POST("3/account/{account_id}/favorite")
    fun markFav(
        @Path("account_id", encoded = true) accountId: String,
        @Query("session_id") sessionId: String,
        @Body body: MarkFavBody
    ): Observable<MarkFavRes>

    // fav
    @GET("3/account/{account_id}/favorite/movies")
    fun fav(
        @Path("account_id", encoded = true) accountId: String,
        @Query("session_id") sessionId: String,
        @Query("language") lang: String = DEF_LANG,
        @Query("sort_by") sortedBy: String = "created_at.asc",
        @Query("page") action: Int = DEF_PAGE
    ): Observable<FavRes>


    // Service access
    companion object {

        const val DEF_LANG = "en-US"
        const val DEF_PAGE = 1
        private const val CONTENT_TYPE = "application/json;charset=utf-8"
        private const val AUTH =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJmMGM1YzZhOGUyNzA4NWU3MDViODFmODAxNDMzMmY4NSIsInN1YiI6IjVmMDExMjZjYTI4NGViMDAzOGEwZTMwMiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.BCYDodK6_34PK_UzZfz1YkykE-MU-6QJuW4d7E9tFFw"

        fun create(): MovieApi {

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

            return retrofit.create(MovieApi::class.java)
        }
    }
}