package com.aligkts.movie.data.movie.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("Response")
    val response: String?,

    @field:SerializedName("totalResults")
    val totalResults: String?,

    @field:SerializedName("Search")
    val search: List<MovieItemResponse>
)