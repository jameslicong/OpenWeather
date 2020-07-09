package com.smilefactory.openweather.utilities.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableTransformer
import io.reactivex.rxjava3.core.FlowableTransformer
import io.reactivex.rxjava3.core.ObservableTransformer
import io.reactivex.rxjava3.core.SingleTransformer
import io.reactivex.rxjava3.schedulers.Schedulers

class RxSchedulerUtilsImp : RxSchedulerUtils {

    override fun forCompletable(): CompletableTransformer {
        return CompletableTransformer {
            it.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        }
    }

    override fun <T> forFlowable(): FlowableTransformer<T, T> {
        return FlowableTransformer {
            it.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        }
    }

    override fun <T> forObservable(): ObservableTransformer<T, T> {
        return ObservableTransformer {
            it.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        }
    }

    override fun <T> forSingle(): SingleTransformer<T, T> {
        return SingleTransformer {
            it.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
        }
    }
}