package com.bassam.moviesinc.injection

import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.interactors.GetDetails
import com.bassam.moviesinc.interactors.GetDetailsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Bassam Hamada on 7/10/20.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object GetDetailsModule {

    @Provides
    fun provideGetDetails(movieApi: MovieApi): GetDetails {
        return GetDetailsImpl(movieApi)
    }
}