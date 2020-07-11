package com.smilefactory.openweather.repository.remote.api

import io.reactivex.Single


interface ApiFactory {

    fun <T> create(apiClass: Class<T>): Single<T>
}