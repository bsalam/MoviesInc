package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

data class SessionRes(
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("success")
    val success: Boolean
)