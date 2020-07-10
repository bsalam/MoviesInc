package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

data class RequestTokenBody(
    @SerializedName("redirect_to")
    val redirectTo: String
)