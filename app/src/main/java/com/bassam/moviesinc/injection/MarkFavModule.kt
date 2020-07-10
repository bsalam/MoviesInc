package com.bassam.moviesinc.injection

import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.interactors.Auth
import com.bassam.moviesinc.interactors.MarkFav
import com.bassam.moviesinc.interactors.MarkFavImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Bassam Hamada on 7/10/20.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object MarkFavModule {

    @Provides
    fun provideMarkFav(
        movieApi: MovieApi,
        auth: Auth
    ): MarkFav {
        return MarkFavImpl(movieApi, auth)
    }
}