package com.bassam.moviesinc.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bassam.moviesinc.api.data.Result
import com.bassam.moviesinc.interactors.GetFav
import com.bassam.moviesinc.interactors.GetNowPlaying
import com.bassam.moviesinc.interactors.MarkFav
import com.bassam.moviesinc.ui.common.BaseViewModel
import com.bassam.moviesinc.utils.SingleLiveEvent
import kotlinx.coroutines.launch

/**
 * Created by Bassam Hamada on 7/7/20.
 */
class MovieItemListViewModel @ViewModelInject constructor(
    private val getNowPlaying: GetNowPlaying,
    private val getFav: GetFav,
    private val markFav: MarkFav
) :
    BaseViewModel() {

    private val results = MutableLiveData<List<Result>>()
    private val addedToFav = SingleLiveEvent<Boolean>()
    var isFav = false

    fun load() {
        loading.value = true
        if (isFav) {

            // load fav items
            viewModelScope.launch(exceptionHandler) {
                results.postValue(getFav.getFav())
                loading.value = false
            }
        } else {

            // load now playing
            viewModelScope.launch(exceptionHandler) {
                results.postValue(getNowPlaying.getNowPlaying())
                loading.value = false
            }
        }
    }

    fun getResults(): LiveData<List<Result>> {
        return results
    }

    fun isAddedToFav(): LiveData<Boolean> {
        return addedToFav
    }

    fun markFav(fav: Boolean, movieId: Int) {
        viewModelScope.launch(exceptionHandler) {
            markFav.markFav(fav, movieId)
            if (fav)
                addedToFav.postValue(true)
            else
                load()
        }
    }

}