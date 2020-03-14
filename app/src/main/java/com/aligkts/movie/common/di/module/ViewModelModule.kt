package com.aligkts.movie.common.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aligkts.movie.common.di.ViewModelFactory
import com.aligkts.movie.common.di.key.ViewModelKey
import com.aligkts.movie.ui.movie.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by Ali Göktaş on 13,March,2020
 */
@Module
abstract class ViewModelModule {

    @IntoMap
    @Binds
    @ViewModelKey(MovieViewModel::class)
    abstract fun provideMoviesViewModel(moviesViewModel: MovieViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}