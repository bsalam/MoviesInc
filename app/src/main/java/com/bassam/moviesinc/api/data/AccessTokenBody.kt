package com.bassam.moviesinc.api.data


/**
 * Created by Bassam Hamada on 7/7/20.
 */
import com.google.gson.annotations.SerializedName

data class AccessTokenBody(
    @SerializedName("request_token")
    val requestToken: String
)