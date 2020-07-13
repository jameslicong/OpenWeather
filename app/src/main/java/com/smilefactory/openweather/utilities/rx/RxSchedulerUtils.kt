package com.smilefactory.openweather.utilities.rx

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer


interface RxSchedulerUtils {

    fun forCompletable(): CompletableTransformer

    fun <T> forFlowable(): FlowableTransformer<T, T>

    fun <T> forObservable(): ObservableTransformer<T, T>

    fun <T> forSingle(): SingleTransformer<T, T>
}