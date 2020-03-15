package com.aligkts.movie.data.movie.response

import com.google.gson.annotations.SerializedName

data class MovieItemResponse(

    @field:SerializedName("Type")
    val type: String?,

    @field:SerializedName("Year")
    val year: String?,

    @field:SerializedName("imdbID")
    val imdbID: String?,

    @field:SerializedName("Poster")
    val poster: String?,

    @field:SerializedName("Title")
    val title: String?
)