package com.bassam.moviesinc.injection

import com.bassam.moviesinc.interactors.SplashInteractor
import com.bassam.moviesinc.interactors.SplashInteractorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Bassam Hamada on 7/7/20.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object SplashInteractorModule {

    @Provides
    fun provideSplashInteractor(): SplashInteractor {
        return SplashInteractorImpl()
    }
}