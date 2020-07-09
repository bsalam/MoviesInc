package com.bassam.moviesinc.api

import com.bassam.moviesinc.api.data.*
import retrofit2.http.*

/**
 * Created by Bassam Hamada on 7/9/20.
 */
interface MovieApi {

    companion object {
        const val DEF_LANG = "en-US"
        const val DEF_PAGE = 1
    }

    // request token
    @POST("4/auth/request_token")
    suspend fun requestToken(): RequestTokenRes

    // access token
    @POST("4/auth/access_token")
    suspend fun accessToken(@Body body: AccessTokenBody): AccessTokenRes

    // session
    @POST("3/authentication/session/convert/4")
    suspend fun session(@Body body: SessionBody): SessionRes

    // now playing
    @GET("3/movie/now_playing")
    suspend fun nowPlaying(
        @Query("language") lang: String = DEF_LANG,
        @Query("page") action: Int = DEF_PAGE
    ): NowPlayingRes

    // details
    @GET("3/movie/{movie_id}")
    suspend fun details(
        @Path("movie_id", encoded = true) movieId: String,
        @Query("language") lang: String = DEF_LANG
    ): DetailsRes

    // recommendation
    @GET("3/movie/{movie_id}/recommendations")
    suspend fun recommendations(
        @Path("movie_id", encoded = true) movieId: String,
        @Query("language") lang: String = DEF_LANG,
        @Query("page") action: Int = DEF_PAGE
    ): RecommendationsRes

    // rate
    @POST("3/movie/{movie_id}/rating")
    suspend fun rate(
        @Path("movie_id", encoded = true) movieId: String,
        @Query("session_id") sessionId: String,
        @Body body: RateBody
    ): RateRes

    // mark fav
    @POST("3/account/{account_id}/favorite")
    suspend fun markFav(
        @Path("account_id", encoded = true) accountId: String,
        @Query("session_id") sessionId: String,
        @Body body: MarkFavBody
    ): MarkFavRes

    // fav
    @GET("3/account/{account_id}/favorite/movies")
    suspend fun fav(
        @Path("account_id", encoded = true) accountId: String,
        @Query("session_id") sessionId: String,
        @Query("language") lang: String = DEF_LANG,
        @Query("sort_by") sortedBy: String = "created_at.asc",
        @Query("page") action: Int = DEF_PAGE
    ): FavRes
}