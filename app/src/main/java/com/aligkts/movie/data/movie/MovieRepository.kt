package com.aligkts.movie.data.movie

import com.aligkts.movie.common.Resource
import com.aligkts.movie.common.ui.applyLoading
import com.aligkts.movie.data.movie.response.MovieResponse
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Ali Göktaş on 13,March,2020
 */

class MovieRepository @Inject constructor(private val movieRemoteDataSource: MovieRemoteDataSource) {

    fun fetchMovies(searchText: String, page: Int): Observable<Resource<MovieResponse>> =
        movieRemoteDataSource
            .fetchMovies(searchText, page)
            .map { Resource.success(it) }
            .onErrorReturn { Resource.error(it) }
            .subscribeOn(Schedulers.io())
            .compose(applyLoading())

}