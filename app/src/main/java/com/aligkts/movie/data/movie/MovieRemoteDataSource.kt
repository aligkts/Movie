package com.aligkts.movie.data.movie

import com.aligkts.movie.data.movie.MovieRestInterface
import javax.inject.Inject

/**
 * Created by Ali Göktaş on 13,March,2020
 */
class MovieRemoteDataSource @Inject constructor(private val movieRestInterface: MovieRestInterface) {

    fun fetchMovies(searchText: String, page: Int) = movieRestInterface.fetchMovies(searchText, page)

}