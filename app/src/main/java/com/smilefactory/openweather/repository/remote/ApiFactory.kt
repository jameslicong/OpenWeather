package com.smilefactory.openweather.repository.remote

import io.reactivex.rxjava3.core.Single

interface ApiFactory {

    fun <T> create(apiClass: Class<T>): Single<T>
}