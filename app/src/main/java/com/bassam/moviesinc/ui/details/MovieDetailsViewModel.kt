package com.bassam.moviesinc.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bassam.moviesinc.api.data.DetailsRes
import com.bassam.moviesinc.interactors.GetDetails
import com.bassam.moviesinc.interactors.GetRecommendations
import com.bassam.moviesinc.interactors.MarkFav
import com.bassam.moviesinc.interactors.Rate
import com.bassam.moviesinc.ui.common.BaseViewModel
import com.bassam.moviesinc.utils.SingleLiveEvent
import kotlinx.coroutines.launch

/**
 * Created by Bassam Hamada on 7/7/20.
 */
class MovieDetailsViewModel @ViewModelInject constructor(
    private val getDetails: GetDetails,
    private val markFav: MarkFav,
    private val rate: Rate,
    private val recommendations: GetRecommendations
) : BaseViewModel() {

    var movieId = -1

    private val result = MutableLiveData<DetailsRes>()
    private val addedToFav = SingleLiveEvent<Boolean>()
    private val rated = SingleLiveEvent<Boolean>()

    fun load() {

        if (movieId != -1) {
            loading.value = true
            // load fav items
            viewModelScope.launch(exceptionHandler) {
                result.postValue(getDetails.getDetails(movieId))
                loading.value = false
            }
        }
    }

    fun markFav() {
        viewModelScope.launch(exceptionHandler) {
            markFav.markFav(true, movieId)
            addedToFav.postValue(true)
        }
    }

    fun rate(value: Double) {
        viewModelScope.launch(exceptionHandler) {
            rate.rate(movieId, value * 2)
            rated.postValue(true)
        }
    }

    fun getResult(): LiveData<DetailsRes> {
        return result
    }

    fun isAddedToFav(): LiveData<Boolean> {
        return addedToFav
    }

    fun isRated(): LiveData<Boolean> {
        return rated
    }
}