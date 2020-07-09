package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

/**
 * Created by Bassam Hamada on 7/7/20.
 */
data class SessionBody(
    @SerializedName("access_token")
    val accessToken: String
)