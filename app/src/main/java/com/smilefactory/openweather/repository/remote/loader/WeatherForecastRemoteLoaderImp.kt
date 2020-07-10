package com.smilefactory.openweather.repository.remote.loader

import com.smilefactory.openweather.repository.model.WeatherForecast
import com.smilefactory.openweather.repository.remote.api.WeatherApi
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherForecastRemoteLoaderImp
@Inject constructor(private val api: WeatherApi) : WeatherForecastRemoteLoader {

    override fun list(): Single<List<WeatherForecast>> {
        return api.getList()
            .flatMap { Single.just(it.list) }
    }
}

