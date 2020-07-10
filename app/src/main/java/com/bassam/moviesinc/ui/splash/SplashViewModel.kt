package com.bassam.moviesinc.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassam.moviesinc.interactors.AuthInteractor
import com.bassam.moviesinc.interactors.SplashInteractor
import com.bassam.moviesinc.utils.SingleLiveEvent
import kotlinx.coroutines.launch

/**
 * Created by Bassam Hamada on 7/7/20.
 */
class SplashViewModel @ViewModelInject constructor(
    splashInteractor: SplashInteractor,
    authInteractor: AuthInteractor
) :
    ViewModel() {

    private val showLogin = SingleLiveEvent<Boolean>()
    private val showMovieList = SingleLiveEvent<Boolean>()

    init {
        viewModelScope.launch {
            splashInteractor.delay()

            if (authInteractor.isAuth())
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