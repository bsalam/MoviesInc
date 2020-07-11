package com.bassam.moviesinc.interactors

import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.api.data.CreditsRes
import com.bassam.moviesinc.api.data.DetailsRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Bassam Hamada on 7/10/20.
 */

interface GetDetails {
    suspend fun getDetails(movieId: Int): DetailsRes
    suspend fun getCredits(movieId: Int): CreditsRes
}

class GetDetailsImpl @Inject constructor(
    private val client: MovieApi
) : GetDetails {

    override suspend fun getDetails(movieId: Int): DetailsRes = withContext(Dispatchers.IO) {
        client.details(movieId)
    }

    override suspend fun getCredits(movieId: Int): CreditsRes = withContext(Dispatchers.IO) {
        client.credits(movieId)
    }
}