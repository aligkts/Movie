package com.aligkts.movie.domain

import com.aligkts.movie.common.Mapper
import com.aligkts.movie.data.movie.response.MovieItemResponse
import com.aligkts.movie.data.movie.response.MovieResponse
import com.aligkts.movie.ui.movie.model.MovieItem
import javax.inject.Inject

/**
 * Created by Ali Göktaş on 14,March,2020
 */

class MoviesMapper @Inject constructor() : Mapper<MovieResponse, List<MovieItem>> {

    override fun mapFrom(type: MovieResponse): List<MovieItem> {
        type.search.let {
            return it.map { searchItem ->
                MovieItem(
                    type = searchItem.type,
                    year = searchItem.year,
                    imdbID = searchItem.imdbID,
                    poster = searchItem.poster,
                    title = searchItem.title
                )
            }
        }
    }
}
