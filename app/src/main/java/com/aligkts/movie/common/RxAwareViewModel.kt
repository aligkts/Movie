package com.aligkts.movie.common

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Ali Göktaş on 13,March,2020
 */

open class RxAwareViewModel : ViewModel() {

    val disposable = CompositeDisposable()

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onCleared()
    }

}