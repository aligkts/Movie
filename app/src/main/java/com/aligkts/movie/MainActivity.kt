package com.aligkts.movie

import android.app.SearchManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
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
    private lateinit var lastQuery: String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        moviesViewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MovieViewModel::class.java)

        moviesViewModel.getMoviesLiveData().observeNonNull(this) {
            renderMovies(it)
        }

        savedInstanceState.runIfNull {
            fetchMovies("", FIRST_PAGE)
        }
        initMoviesRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.item_search)
        val searchView = searchItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                query?.let {
                    movieAdapter.clearItemList()
                    fetchMovies(it, FIRST_PAGE)
                    lastQuery = it
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
        return true
    }

    private fun initMoviesRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        binding.recyclerView.apply {
            adapter = movieAdapter
            layoutManager = linearLayoutManager
            addOnScrollListener(object : EndlessScrollListener(linearLayoutManager) {
                override fun onLoadMore(page: Int) {
                    fetchMovies(lastQuery, page)
                }
            })
        }
    }

    private fun renderMovies(movieViewState: MovieViewState) {
        with(binding) {
            viewState = movieViewState
            executePendingBindings()
        }
        if (movieAdapter.itemCount > 0)
            movieAdapter.setScrolledMovie(movieViewState.getMovies())
        else
            movieAdapter.setMovie(movieViewState.getMovies())

    }

    private fun fetchMovies(searchText: String, page: Int) {
        if (searchText.isNotEmpty())
            moviesViewModel.fetchMovies(searchText, page)
    }

    companion object {
        const val FIRST_PAGE = 1
    }
}
