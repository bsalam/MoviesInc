package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

/**
 * Created by Bassam Hamada on 7/7/20.
 */
data class RateRes(
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String
)