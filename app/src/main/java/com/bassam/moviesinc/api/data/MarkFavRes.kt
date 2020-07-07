package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

data class MarkFavRes(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String
)