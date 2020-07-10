package com.bassam.moviesinc.ui.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassam.moviesinc.R
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * Created by Bassam Hamada on 7/10/20.
 */
abstract class BaseViewModel : ViewModel() {

    data class Error(var msgRes: Int)

    protected val loading = MutableLiveData<Boolean>()
    private val error = MutableLiveData<Error>()

    protected val exceptionHandler = CoroutineExceptionHandler() { _, _ ->
        loading.postValue(false)
        error.postValue(Error(R.string.error_msg))
    }

    fun isLoading(): LiveData<Boolean> {
        return loading
    }

    fun getError(): LiveData<Error> {
        return error
    }
}

