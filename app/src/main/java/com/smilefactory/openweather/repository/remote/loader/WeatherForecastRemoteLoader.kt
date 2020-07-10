package com.smilefactory.openweather.repository.remote.loader

import com.smilefactory.openweather.repository.model.WeatherForecast
import io.reactivex.rxjava3.core.Single

interface WeatherForecastRemoteLoader {

    fun list(): Single<List<WeatherForecast>>
}