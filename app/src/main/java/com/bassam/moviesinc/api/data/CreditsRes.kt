package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

data class CreditsRes(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("crew")
    val crew: List<Crew>,
    @SerializedName("id")
    val id: Int
)