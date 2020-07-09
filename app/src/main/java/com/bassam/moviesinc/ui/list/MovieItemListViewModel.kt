package com.bassam.moviesinc.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassam.moviesinc.interactors.AuthInteractor
import com.bassam.moviesinc.utils.SingleLiveEvent
import kotlinx.coroutines.launch

/**
 * Created by Bassam Hamada on 7/7/20.
 */
class MovieItemListViewModel @ViewModelInject constructor(authInteractor: AuthInteractor) :
    ViewModel() {

    private val showLogin = SingleLiveEvent<Boolean>()

    init {

        viewModelScope.launch {
            if (!authInteractor.isAuth()) {
                showLogin.postValue(true)
            }
        }
    }

    fun shouldShowLogin(): MutableLiveData<Boolean> {
        return showLogin
    }
}