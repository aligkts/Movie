package com.aligkts.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aligkts.movie.common.ui.EndlessScrollListener
import com.aligkts.movie.common.ui.observeNonNull
import com.aligkts.movie.common.ui.runIfNull
import com.aligkts.movie.databinding.ActivityMainBinding
import com.aligkts.movie.ui.movie.MovieViewModel
import com.aligkts.movie.ui.movie.MovieViewState
import com.aligkts.movie.ui.movie.MoviesAdapter
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    internal lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    @Inject
    internal lateinit var movieAdapter: MoviesAdapter

    private lateinit var moviesViewModel: MovieViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        moviesViewModel =
            ViewModelProviders.of(this, viewModelProviderFactory).get(MovieViewModel::class.java)

        moviesViewModel.getMoviesLiveData().observeNonNull(this) {
            renderMovies(it)
        }

        savedInstanceState.runIfNull {
            fetchMovies("batman", FIRST_PAGE)
        }
        initPopularTVShowsRecyclerView()
    }

    private fun initPopularTVShowsRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = linearLayoutManager
            addOnScrollListener(object : EndlessScrollListener(linearLayoutManager) {
                override fun onLoadMore(page: Int) {
                    fetchMovies("batman", page)
                }
            })
        }
    }

    private fun renderMovies(movieViewState: MovieViewState) {
        with(binding) {
            viewState = movieViewState
            executePendingBindings()
        }
        movieAdapter.setTvShows(movieViewState.getMovies())
    }

    private fun fetchMovies(searchText: String, page: Int) {
        moviesViewModel.fetchMovies(searchText, page)
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}
