package com.aligkts.movie.data.movie

import com.aligkts.movie.data.movie.response.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Ali Göktaş on 13,March,2020
 */
interface MovieRestInterface {

    @GET("/")
    fun fetchMovies(@Query("s") searchText: String,
                    @Query("page") page: Int): Observable<MovieResponse>

}