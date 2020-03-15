package com.aligkts.movie.ui.movie

import com.aligkts.movie.common.Status
import com.aligkts.movie.ui.movie.model.MovieItem

/**
 * Created by Ali Göktaş on 14,March,2020
 */
class MovieViewState(
    val status: Status,
    val error: Throwable? = null,
    val data: List<MovieItem>? = null
) {
    fun getMovies() = data ?: mutableListOf()

    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error?.message

    fun shouldShowErrorMessage() = error != null
}