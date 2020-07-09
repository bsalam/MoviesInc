package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

/**
 * Created by Bassam Hamada on 7/7/20.
 */
data class SpokenLanguage(
    @SerializedName("iso_639_1")
    val iso6391: String,
    @SerializedName("name")
    val name: String
)