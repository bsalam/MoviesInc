package com.bassam.moviesinc.interactors

import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.api.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Bassam Hamada on 7/10/20.
 */
interface GetRecommendations {
    suspend fun getRecommendations(movieId: Int): List<Result>
}

class GetRecommendationsImpl @Inject constructor(
    private val client: MovieApi
) : GetRecommendations {
    override suspend fun getRecommendations(movieId: Int) = withContext(Dispatchers.IO) {
        client.recommendations(movieId).results
    }
}