package com.bassam.moviesinc.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassam.moviesinc.interactors.SplashInteractor
import com.bassam.moviesinc.utils.SingleLiveEvent
import kotlinx.coroutines.launch

/**
 * Created by Bassam Hamada on 7/7/20.
 */
class SplashViewModel @ViewModelInject constructor(splashInteractor: SplashInteractor) :
    ViewModel() {

    private val navigateNext = SingleLiveEvent<Boolean>()

    data class ViewState(
        val isLoading: Boolean = false,
        val url: String? = "test"
    )

    init {
        viewModelScope.launch {
            splashInteractor.delay()
            navigateNext.postValue(true)
        }
    }

    fun isNavigateNext(): LiveData<Boolean> {
        return navigateNext
    }
}