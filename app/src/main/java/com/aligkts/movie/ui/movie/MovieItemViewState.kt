package com.aligkts.movie.ui.movie

import com.aligkts.movie.ui.movie.model.MovieItem

/**
 * Created by Ali Göktaş on 14,March,2020
 */
class MovieItemViewState(private val movieItem: MovieItem) {

    fun getYear() = movieItem.year
    fun getPoster() = movieItem.poster
    fun getTitle() = movieItem.title

}