package com.aligkts.movie.common.di.component

import android.app.Application
import com.aligkts.movie.MovieApplication
import com.aligkts.movie.common.di.module.ActivityBuilderModule
import com.aligkts.movie.common.di.module.DataModule
import com.aligkts.movie.common.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Ali Göktaş on 13,March,2020
 */

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class,
        DataModule::class
    ]
)
interface AppComponent : AndroidInjector<MovieApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MovieApplication>() {
        @BindsInstance
        abstract fun app(application: Application): Builder
    }
}