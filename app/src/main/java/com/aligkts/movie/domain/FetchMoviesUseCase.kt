package com.aligkts.movie.domain

import com.aligkts.movie.common.Resource
import com.aligkts.movie.data.movie.MovieRepository
import com.aligkts.movie.data.movie.response.MovieItemResponse
import com.aligkts.movie.ui.movie.model.Movie
import com.aligkts.movie.ui.movie.model.MovieItem
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Ali Göktaş on 14,March,2020
 */
class FetchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
    private val mapper: MoviesMapper
) {

    fun fetchMovies(searchText: String, page: Int): Observable<Resource<Movie>> {
        return repository
            .fetchMovies(searchText, page)
            .map { resource ->
                Resource(
                    status = resource.status,
                    data = resource.data?.let { mapper.mapFrom(it) },
                    error = resource.error
                )
            }
    }
}