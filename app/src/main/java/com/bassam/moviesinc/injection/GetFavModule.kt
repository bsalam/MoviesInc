package com.bassam.moviesinc.injection

import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.interactors.Auth
import com.bassam.moviesinc.interactors.GetFav
import com.bassam.moviesinc.interactors.GetFavImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Bassam Hamada on 7/10/20.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object GetFavModule {

    @Provides
    fun provideGetFav(
        movieApi: MovieApi,
        auth: Auth
    ): GetFav {
        return GetFavImpl(movieApi, auth)
    }
}