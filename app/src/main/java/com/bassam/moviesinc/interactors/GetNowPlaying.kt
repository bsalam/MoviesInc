package com.bassam.moviesinc.interactors

import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.api.data.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Bassam Hamada on 7/10/20.
 */
interface GetNowPlaying {
    suspend fun getNowPlaying(): List<Result>
}

class GetNowPlayingImpl @Inject constructor(
    private val client: MovieApi
) : GetNowPlaying {
    override suspend fun getNowPlaying() = withContext(Dispatchers.IO) {
        client.nowPlaying().results
    }
}