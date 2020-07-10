package com.bassam.moviesinc.injection

import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.interactors.GetNowPlaying
import com.bassam.moviesinc.interactors.GetNowPlayingImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Bassam Hamada on 7/10/20.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object GetNowPlayingModule {

    @Provides
    fun provideGetNowPlaying(movieApi: MovieApi): GetNowPlaying {
        return GetNowPlayingImpl(movieApi)
    }
}