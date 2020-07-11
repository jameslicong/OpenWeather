package com.smilefactory.openweather.repository.local.loader

import com.smilefactory.openweather.repository.local.room.dao.WeatherForecastDao
import com.smilefactory.openweather.repository.model.WeatherForecast
import io.reactivex.Single
import javax.inject.Inject

class WeatherForecastLocalLoaderImp
@Inject constructor(private val dao: WeatherForecastDao) : WeatherForecastLocalLoader {

    override fun all(): Single<List<WeatherForecast>> {
        return dao.all()
    }

    override fun byName(name: String): Single<WeatherForecast> {
        return dao.byName(name)
    }
}