package com.bassam.moviesinc.interactors

import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.api.data.MarkFavBody
import com.bassam.moviesinc.api.data.MarkFavRes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Bassam Hamada on 7/10/20.
 */
interface MarkFav {
    suspend fun markFav(fav: Boolean, id: Int, type: String): MarkFavRes
}

class MarkFavImpl @Inject constructor(
    private val client: MovieApi,
    private val auth: Auth
) : MarkFav {
    override suspend fun markFav(fav: Boolean, id: Int, type: String) =
        withContext(Dispatchers.IO) {
            client.markFav(auth.getAccountId(), auth.getSessionId(), MarkFavBody(fav, id, type))
        }
}