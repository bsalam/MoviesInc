package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

data class AccessTokenRes(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("account_id")
    val accountId: String,
    @SerializedName("status_code")
    val statusCode: Int,
    @SerializedName("status_message")
    val statusMessage: String,
    @SerializedName("success")
    val success: Boolean
)