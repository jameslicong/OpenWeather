package com.smilefactory.openweather.repository.local.saver

import com.smilefactory.openweather.repository.model.WeatherForecast
import io.reactivex.Completable

interface WeatherForecastLocalSaver {

    fun add(weatherForecast: WeatherForecast): Completable

    fun asFavorite(isFavorite: Boolean, cityName: String): Completable
}