package com.aligkts.movie.common

/**
 * Created by Ali Göktaş on 13,March,2020
 */

interface Mapper<R, D> {
    fun mapFrom(type: R): D
}