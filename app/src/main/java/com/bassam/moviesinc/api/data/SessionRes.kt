package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

/**
 * Created by Bassam Hamada on 7/7/20.
 */
data class SessionRes(
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("success")
    val success: Boolean
)