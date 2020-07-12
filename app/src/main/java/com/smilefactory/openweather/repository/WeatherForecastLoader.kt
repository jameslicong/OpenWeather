package com.smilefactory.openweather.repository

import com.smilefactory.openweather.repository.model.WeatherForecast
import io.reactivex.Single

interface WeatherForecastLoader {

    fun all(): Single<List<WeatherForecast>>

    fun details(cityName: String): Single<WeatherForecast>

    fun localDetails(cityName: String): Single<WeatherForecast>
}