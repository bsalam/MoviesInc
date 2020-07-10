package com.bassam.moviesinc.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bassam.moviesinc.interactors.Auth
import com.bassam.moviesinc.interactors.Delay
import com.bassam.moviesinc.ui.common.BaseViewModel
import com.bassam.moviesinc.utils.SingleLiveEvent
import kotlinx.coroutines.launch

/**
 * Created by Bassam Hamada on 7/7/20.
 */
class SplashViewModel @ViewModelInject constructor(
    delay: Delay,
    auth: Auth
) :
    BaseViewModel() {

    private val showLogin = SingleLiveEvent<Boolean>()
    private val showMovieList = SingleLiveEvent<Boolean>()

    init {
        viewModelScope.launch(exceptionHandler) {
            delay.delay()

            if (auth.isAuth())
                showMovieList.value = true
            else
                showLogin.value = true
        }
    }

    fun isShowLogin(): LiveData<Boolean> {
        return showLogin
    }

    fun isShowMovieList(): LiveData<Boolean> {
        return showMovieList
    }
}