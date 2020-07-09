package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

/**
 * Created by Bassam Hamada on 7/7/20.
 */
data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)