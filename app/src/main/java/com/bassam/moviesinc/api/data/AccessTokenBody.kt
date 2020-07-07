package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

data class AccessTokenBody(
    @SerializedName("request_token")
    val requestToken: String
)