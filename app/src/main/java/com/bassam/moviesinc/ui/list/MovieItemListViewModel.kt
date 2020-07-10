package com.bassam.moviesinc.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bassam.moviesinc.ui.common.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Created by Bassam Hamada on 7/7/20.
 */
class MovieItemListViewModel @ViewModelInject constructor() :
    BaseViewModel() {

    var isFav = false

    init {

        if(isFav) {

            // load fav items
        } else {
            loading.value = true
//            viewModelScope.launch(exceptionHandler) {
//                // create request token and generate utl
//                val url = authInteractor.getApproveUrl()
//                loading.value = false
//                this@LoginViewModel.url.value = url
//            }

        }
    }
}