package com.aligkts.movie.ui.movie

import com.aligkts.movie.common.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by Ali Göktaş on 14,March,2020
 */
@Module
class MovieActivityModule {

    @Provides
    @ActivityScope
    fun provideMoviesAdapter(): MoviesAdapter {
        return MoviesAdapter()
    }
}