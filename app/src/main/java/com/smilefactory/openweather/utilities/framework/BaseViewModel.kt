package com.smilefactory.openweather.utilities.framework

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    lateinit var subscriptions: Disposable

    private var compositeDisposable : CompositeDisposable = CompositeDisposable()

    override fun onCleared() {
        subscriptions.dispose()
        compositeDisposable.clear()
        super.onCleared()
    }

    fun addDisposable(disposable: Disposable) {
        compositeDisposable.addAll(disposable)
    }
}