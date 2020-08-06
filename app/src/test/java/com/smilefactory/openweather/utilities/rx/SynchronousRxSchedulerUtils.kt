package com.smilefactory.openweather.utilities.rx

import io.reactivex.*
import javax.inject.Inject

class SynchronousRxSchedulerUtils
    @Inject constructor(): RxSchedulerUtils {

    override fun forCompletable(): CompletableTransformer {
        return CompletableTransformer { upstream: Completable -> upstream }
    }

    override fun <T> forFlowable(): FlowableTransformer<T, T> {
        return FlowableTransformer { upstream: Flowable<T> -> upstream }
    }

    override fun <T> forObservable(): ObservableTransformer<T, T> {
        return ObservableTransformer { upstream: Observable<T> -> upstream }
    }

    override fun <T> forSingle(): SingleTransformer<T, T> {
        return SingleTransformer { upstream: Single<T> -> upstream }
    }
}