package com.bassam.moviesinc.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bassam.moviesinc.api.data.DetailsRes
import com.bassam.moviesinc.api.data.Result
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

    companion object {
        private const val CAST_SEPARATOR = "\n"
        private const val CAST_CHAR_SEPARATOR = " --- "

    }

    var movieId = -1

    private val result = MutableLiveData<DetailsRes>()
    private val recommendationsResult = MutableLiveData<List<Result>>()
    private val cast = MutableLiveData<String>()
    private val addedToFav = SingleLiveEvent<Boolean>()
    private val rated = SingleLiveEvent<Boolean>()

    fun load() {

        if (movieId != -1) {
            loading.value = true
            // load fav items
            viewModelScope.launch(exceptionHandler) {

                // get movie details
                result.postValue(getDetails.getDetails(movieId))

                // get cast details
                cast.postValue(getDetails.getCredits(movieId).cast.joinToString(
                    separator = CAST_SEPARATOR
                ) {
                    it.name + CAST_CHAR_SEPARATOR + it.character
                })

                // get recommendations list
                recommendationsResult.postValue(recommendations.getRecommendations(movieId))
                
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

    fun getRecommendationsResult(): LiveData<List<Result>> {
        return recommendationsResult
    }

    fun getCast(): LiveData<String> {
        return cast
    }

    fun isAddedToFav(): LiveData<Boolean> {
        return addedToFav
    }

    fun isRated(): LiveData<Boolean> {
        return rated
    }
}