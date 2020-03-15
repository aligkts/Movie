package com.aligkts.movie.common.di.module

import com.aligkts.movie.data.NetworkModule
import dagger.Module

/**
 * Created by Ali Göktaş on 13,March,2020
 */
@Module(
    includes = [NetworkModule::class]
)
class DataModule