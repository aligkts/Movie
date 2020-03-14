package com.aligkts.movie.data.movie

import com.aligkts.movie.data.movie.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Ali Göktaş on 13,March,2020
 */
interface MovieRestInterface {

    @GET
    fun fetchMovies(@Path("s") searchText: String,
                    @Path("page") page: Int): Observable<MovieResponse>

}