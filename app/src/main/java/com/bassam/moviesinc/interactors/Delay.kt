package com.bassam.moviesinc.interactors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Bassam Hamada on 7/7/20.
 */
private const val splashDelay = 1500L

interface Delay {
    suspend fun delay()
}

class DelayImpl @Inject constructor() : Delay {

    override suspend fun delay() = withContext(Dispatchers.Default) {
        kotlinx.coroutines.delay(splashDelay)
    }
}