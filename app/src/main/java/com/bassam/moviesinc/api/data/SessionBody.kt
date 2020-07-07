package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

data class SessionBody(
    @SerializedName("access_token")
    val accessToken: String
)