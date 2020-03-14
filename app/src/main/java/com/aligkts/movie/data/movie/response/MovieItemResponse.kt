package com.aligkts.movie.data.movie.response

import com.google.gson.annotations.SerializedName

data class MovieItemResponse(

    @field:SerializedName("Type")
    val type: String? = null,

    @field:SerializedName("Year")
    val year: String? = null,

    @field:SerializedName("imdbID")
    val imdbID: String? = null,

    @field:SerializedName("Poster")
    val poster: String? = null,

    @field:SerializedName("Title")
    val title: String? = null
)