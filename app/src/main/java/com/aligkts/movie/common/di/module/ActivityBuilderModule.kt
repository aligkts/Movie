package com.aligkts.movie.common.di.module

import com.aligkts.movie.MainActivity
import com.aligkts.movie.common.di.scope.ActivityScope
import com.aligkts.movie.ui.movie.MovieActivityModule
import com.aligkts.movie.ui.splash.SplashActivity
import com.aligkts.movie.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Ali Göktaş on 13,March,2020
 */
@Module
abstract class ActivityBuilderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [SplashActivityModule::class])
    abstract fun bindSplashActivity(): SplashActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [MovieActivityModule::class])
    abstract fun bindMainActivity(): MainActivity
}