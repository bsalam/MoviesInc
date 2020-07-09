package com.bassam.moviesinc.interactors

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Bassam Hamada on 7/7/20.
 */
private const val splashDelay = 2000L

interface SplashInteractor {
    suspend fun delay()
}

class SplashInteractorImpl @Inject constructor() : SplashInteractor {

    override suspend fun delay() = withContext(Dispatchers.Default) {
        kotlinx.coroutines.delay(splashDelay)
    }
}