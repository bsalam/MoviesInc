package com.bassam.moviesinc.ui.splash

import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassam.moviesinc.utils.OneTimeEvent
import com.bassam.moviesinc.utils.toOneTimeEvent

class SplashViewModel : ViewModel() {

    private val splashDelay = 3000L

    private val ready = MutableLiveData<OneTimeEvent<Any>>()

    init {
        Handler().postDelayed({
            ready.postValue({}.toOneTimeEvent())
        }, splashDelay)
    }

    fun isReady(): MutableLiveData<OneTimeEvent<Any>> {
        return ready
    }
}