package com.aligkts.movie.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aligkts.movie.common.Resource
import com.aligkts.movie.common.RxAwareViewModel
import com.aligkts.movie.common.ui.plusAssign
import com.aligkts.movie.domain.FetchMoviesUseCase
import com.aligkts.movie.ui.movie.model.Movie
import com.aligkts.movie.ui.movie.model.MovieItem
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

/**
 * Created by Ali Göktaş on 14,March,2020
 */
class MovieViewModel @Inject constructor(private val fetchMoviewsUseCase: FetchMoviesUseCase) :
    RxAwareViewModel() {

    private val moviesLiveData = MutableLiveData<MovieViewState>()

    fun getMoviesLiveData(): LiveData<MovieViewState> = moviesLiveData

    fun fetchMovies(searchText: String, page: Int) {
        fetchMoviewsUseCase
            .fetchMovies(searchText, page)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::onMoviesResultReady)
            .also {
                disposable += it
            }
    }

    private fun onMoviesResultReady(resource: Resource<List<MovieItem>>) {
        moviesLiveData.value = MovieViewState(
            status = resource.status,
            error = resource.error,
            data = resource.data
        )
    }
}