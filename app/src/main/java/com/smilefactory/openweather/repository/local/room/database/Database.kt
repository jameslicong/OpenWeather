package com.smilefactory.openweather.repository.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.smilefactory.openweather.BuildConfig
import com.smilefactory.openweather.repository.local.room.converter.WeatherForecastConverter
import com.smilefactory.openweather.repository.local.room.dao.WeatherForecastDao
import com.smilefactory.openweather.repository.model.WeatherForecast

@Database(
    entities = [WeatherForecast::class],
    version = BuildConfig.DATABASE_VERSION,
    exportSchema = true
)
@TypeConverters(
    value = [WeatherForecastConverter::class]
)
abstract class Database : RoomDatabase() {

    abstract fun weatherForecastDao(): WeatherForecastDao
}