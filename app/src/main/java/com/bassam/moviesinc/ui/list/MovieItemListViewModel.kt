package com.bassam.moviesinc.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassam.moviesinc.utils.OneTimeEvent
import com.bassam.moviesinc.utils.toOneTimeEvent

class MovieItemListViewModel : ViewModel() {

    private val showLogin = MutableLiveData<OneTimeEvent<Any>>()

    init {
        showLogin.postValue({}.toOneTimeEvent())
    }

    fun shouldShowLogin(): MutableLiveData<OneTimeEvent<Any>> {
        return showLogin
    }
}