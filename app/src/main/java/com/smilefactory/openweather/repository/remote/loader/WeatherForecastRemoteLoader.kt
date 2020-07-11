package com.smilefactory.openweather.repository.remote.loader

import com.smilefactory.openweather.repository.model.WeatherForecast
import io.reactivex.Single

interface WeatherForecastRemoteLoader {

    fun list(): Single<List<WeatherForecast>>

    fun details(city: String): Single<WeatherForecast>
}