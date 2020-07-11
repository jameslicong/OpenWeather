package com.smilefactory.openweather.utilities.rx

import io.reactivex.CompletableTransformer
import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


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