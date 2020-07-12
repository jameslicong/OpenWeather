package com.smilefactory.openweather.repository

import io.reactivex.Completable

interface WeatherForecastUpdater {

    fun setFavorite(isFavorite: Boolean, cityName: String): Completable
}