package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

data class RateBody(
    @SerializedName("value")
    val value: Double
)