package com.aligkts.movie.ui.movie

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aligkts.movie.R
import com.aligkts.movie.common.ui.inflate
import com.aligkts.movie.databinding.ItemMovieBinding
import com.aligkts.movie.ui.movie.model.MovieItem
import javax.inject.Inject

/**
 * Created by Ali Göktaş on 14,March,2020
 */
class MoviesAdapter @Inject constructor() :
    RecyclerView.Adapter<MoviesAdapter.MovieItemViewHolder>() {

    private var movieItems: MutableList<MovieItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val itemBinding = parent.inflate<ItemMovieBinding>(R.layout.item_movie, false)
        return MovieItemViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = movieItems.size

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        holder.bind(getMovies(position))
    }

    private fun getMovies(position: Int) = movieItems[position]

    fun setScrolledMovie(movie: List<MovieItem>) {
        val beforeSize = movieItems.size
        movieItems.addAll(movie)
        notifyItemRangeInserted(beforeSize, movie.size)
    }

    fun setMovie(movies: List<MovieItem>) {
        movieItems.addAll(movies)
        notifyDataSetChanged()
    }

    fun clearItemList() {
        movieItems.clear()
    }

    inner class MovieItemViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movieItem: MovieItem) {
            with(binding) {
                viewState = MovieItemViewState(movieItem)
                executePendingBindings()
            }
        }

    }
}