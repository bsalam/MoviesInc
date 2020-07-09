package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

/**
 * Created by Bassam Hamada on 7/7/20.
 */
data class FavRes(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)