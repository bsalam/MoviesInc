package com.bassam.moviesinc.ui.login

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bassam.moviesinc.interactors.AuthInteractor
import com.bassam.moviesinc.ui.common.BaseViewModel
import com.bassam.moviesinc.utils.SingleLiveEvent
import kotlinx.coroutines.launch

/**
 * Created by Bassam Hamada on 7/7/20.
 */
class LoginViewModel @ViewModelInject constructor(private val authInteractor: AuthInteractor) :
    BaseViewModel() {

    val url = MutableLiveData<String>()

    val navigateNext = SingleLiveEvent<Boolean>()

    init {
        loading.value = true
        viewModelScope.launch(exceptionHandler) {
            // create request token and generate utl
            val url = authInteractor.getApproveUrl()
            loading.value = false
            this@LoginViewModel.url.value = url
        }
    }

    fun captureUrl(url: String) {
        Log.d("Login", "Captured Url: $url")
        loading.value = true
        viewModelScope.launch(exceptionHandler) {
            if (authInteractor.isUrlApproved(url)) {
                // generate access token, and session ID
                authInteractor.completeAuth()
                loading.value = false
                navigateNext.value = true
            } else {
                this@LoginViewModel.url.value = url
                loading.value = false
            }
        }
    }
}