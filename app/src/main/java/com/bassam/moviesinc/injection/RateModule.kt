package com.bassam.moviesinc.injection

import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.interactors.Auth
import com.bassam.moviesinc.interactors.Rate
import com.bassam.moviesinc.interactors.RateImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Bassam Hamada on 7/10/20.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object RateModule {

    @Provides
    fun provideRate(
        movieApi: MovieApi,
        auth: Auth
    ): Rate {
        return RateImpl(movieApi, auth)
    }
}