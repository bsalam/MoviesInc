package com.bassam.moviesinc.interactors

import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.api.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Bassam Hamada on 7/10/20.
 */
interface GetFav {
    suspend fun getFav(): List<Result>
}

class GetFavImpl @Inject constructor(
    private val client: MovieApi,
    private val auth: Auth
) : GetFav {
    override suspend fun getFav() = withContext(Dispatchers.IO) {
        client.fav(auth.getAccountId(), auth.getSessionId()).results
    }
}