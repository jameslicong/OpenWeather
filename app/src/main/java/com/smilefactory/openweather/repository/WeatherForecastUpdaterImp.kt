package com.smilefactory.openweather.repository

import com.smilefactory.openweather.repository.local.saver.WeatherForecastLocalSaver
import io.reactivex.Completable
import javax.inject.Inject

class WeatherForecastUpdaterImp
@Inject constructor(private val localSaver: WeatherForecastLocalSaver) : WeatherForecastUpdater {

    override fun setFavorite(isFavorite: Boolean, cityName: String): Completable {
        return localSaver.asFavorite(isFavorite, cityName)
    }
}