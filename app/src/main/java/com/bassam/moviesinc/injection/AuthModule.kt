package com.bassam.moviesinc.injection

import android.content.Context
import android.content.SharedPreferences
import com.bassam.moviesinc.api.MovieApi
import com.bassam.moviesinc.interactors.Auth
import com.bassam.moviesinc.interactors.AuthImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

/**
 * Created by Bassam Hamada on 7/7/20.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object AuthModule {

    @Provides
    fun provideAuthInteractor(
        @ApplicationContext appContext: Context,
        movieApi: MovieApi,
        sharedPreferences: SharedPreferences
    ): Auth {
        return AuthImpl(appContext, movieApi, sharedPreferences)
    }
}