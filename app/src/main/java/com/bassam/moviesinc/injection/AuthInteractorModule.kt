package com.bassam.moviesinc.injection

import android.content.Context
import com.bassam.moviesinc.interactors.AuthInteractor
import com.bassam.moviesinc.interactors.AuthInteractorImpl
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
object AuthInteractorModule {

    @Provides
    fun provideAuthInteractor(@ApplicationContext appContext: Context): AuthInteractor {
        return AuthInteractorImpl(appContext)
    }
}