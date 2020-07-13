package com.smilefactory.openweather.repository.local.loader

import com.smilefactory.openweather.repository.model.WeatherForecast
import io.reactivex.Single

interface WeatherForecastLocalLoader {

    fun all(): Single<List<WeatherForecast>>

    fun byName(name: String): Single<WeatherForecast>
}
