package com.bassam.moviesinc.injection

import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.interactors.GetRecommendations
import com.bassam.moviesinc.interactors.GetRecommendationsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Bassam Hamada on 7/10/20.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object GetRecommendationsModule {

    @Provides
    fun provideGetRecommendations(movieApi: MovieApi): GetRecommendations {
        return GetRecommendationsImpl(movieApi)
    }
}