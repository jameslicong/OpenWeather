package com.smilefactory.openweather.repository.local.room.dao

import com.smilefactory.openweather.repository.local.room.database.Database
import dagger.Module
import dagger.Provides

@Module
class DaoProviderModule {

    @Provides
    fun provideWeatherForecastDao(database: Database): WeatherForecastDao {
        return database.weatherForecastDao()
    }
}