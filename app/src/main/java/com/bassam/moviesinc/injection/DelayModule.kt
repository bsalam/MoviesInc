package com.bassam.moviesinc.injection

import com.bassam.moviesinc.interactors.Delay
import com.bassam.moviesinc.interactors.DelayImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

/**
 * Created by Bassam Hamada on 7/7/20.
 */
@Module
@InstallIn(ActivityRetainedComponent::class)
object DelayModule {

    @Provides
    fun provideSplashInteractor(): Delay {
        return DelayImpl()
    }
}