package com.smilefactory.openweather.repository.local.saver

import com.smilefactory.openweather.repository.local.room.dao.WeatherForecastDao
import com.smilefactory.openweather.repository.model.WeatherForecast
import io.reactivex.Completable
import javax.inject.Inject

class WeatherForecastLocalSaverImp
@Inject constructor(private val dao: WeatherForecastDao) : WeatherForecastLocalSaver {

    override fun add(weatherForecast: WeatherForecast): Completable {
        return Completable.fromAction {
            dao.insert(weatherForecast)
        }
    }
}