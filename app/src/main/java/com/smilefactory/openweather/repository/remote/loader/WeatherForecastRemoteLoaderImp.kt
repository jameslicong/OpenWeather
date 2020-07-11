package com.smilefactory.openweather.repository.remote.loader

import com.smilefactory.openweather.repository.model.WeatherForecast
import com.smilefactory.openweather.repository.remote.api.ApiFactory
import com.smilefactory.openweather.repository.remote.api.WeatherApi
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class WeatherForecastRemoteLoaderImp
@Inject constructor(private val apiFactory: ApiFactory) : WeatherForecastRemoteLoader {

    override fun list(): Single<List<WeatherForecast>> {
        return apiFactory.create(WeatherApi::class.java)
            .flatMap { weatherApi -> weatherApi.getList() }
            .flatMap { response -> Single.just(response.list) }
    }

    override fun details(city: String): Single<WeatherForecast> {
        return apiFactory.create(WeatherApi::class.java)
            .flatMap { weatherApi -> weatherApi.getDetails(city = city ) }
    }
}

