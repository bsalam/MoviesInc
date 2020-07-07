package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

data class RequestTokenRes(
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("success")
    val success: Boolean
)