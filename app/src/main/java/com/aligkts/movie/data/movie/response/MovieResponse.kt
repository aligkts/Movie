package com.aligkts.movie.data.movie.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("Response")
    val response: String? = null,

    @field:SerializedName("totalResults")
    val totalResults: String? = null,

    @field:SerializedName("Search")
    val search: List<MovieItemResponse?>? = null
)