package com.bassam.moviesinc.ui.login

import android.util.Log
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
class LoginViewModel @ViewModelInject constructor(private val authInteractor: AuthInteractor) :
    ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val url = MutableLiveData<String>()
    val isError = MutableLiveData<Boolean>()
    val navigateNext = SingleLiveEvent<Boolean>()

    init {
        isLoading.value = true
        viewModelScope.launch {
            // create request token and generate utl
            val url = authInteractor.getApproveUrl()
            isLoading.value = false
            this@LoginViewModel.url.value = url
        }
    }

    fun captureUrl(url: String) {
        Log.d("Login", "Captured Url: $url")
        isLoading.value = true
        viewModelScope.launch {
            if (authInteractor.isUrlApproved(url)) {
                // generate access token, and session ID
                authInteractor.completeAuth()
                isLoading.value = false
                navigateNext.value = true
            } else {
                this@LoginViewModel.url.value = url
                isLoading.value = false
                isError.value = true
            }
        }
    }
}