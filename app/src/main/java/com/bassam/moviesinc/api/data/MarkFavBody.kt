package com.bassam.moviesinc.api.data


import com.google.gson.annotations.SerializedName

data class MarkFavBody(
    @SerializedName("favorite")
    val favorite: Boolean,
    @SerializedName("media_id")
    val mediaId: Int,
    @SerializedName("media_type")
    val mediaType: String
)