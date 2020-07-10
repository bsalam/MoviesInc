package com.bassam.moviesinc.interactors

import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.api.data.RateBody
import com.bassam.moviesinc.api.data.RateRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Bassam Hamada on 7/10/20.
 */
interface Rate {
    suspend fun rate(movieId: String, value: Double): RateRes
}

class RateImpl @Inject constructor(
    private val client: MovieApi,
    private val auth: Auth
) : Rate {
    override suspend fun rate(movieId: String, value: Double) = withContext(Dispatchers.IO) {
        client.rate(movieId, auth.getSessionId(), RateBody(value))
    }
}