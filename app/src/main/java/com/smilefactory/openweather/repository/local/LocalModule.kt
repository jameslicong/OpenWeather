package com.smilefactory.openweather.repository.local

import com.smilefactory.openweather.repository.local.loader.WeatherForecastLocalLoader
import com.smilefactory.openweather.repository.local.loader.WeatherForecastLocalLoaderImp
import com.smilefactory.openweather.repository.local.room.dao.DaoProviderModule
import com.smilefactory.openweather.repository.local.room.database.DatabaseModule
import com.smilefactory.openweather.repository.local.saver.WeatherForecastLocalSaver
import com.smilefactory.openweather.repository.local.saver.WeatherForecastLocalSaverImp
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        DaoProviderModule::class,
        DatabaseModule::class
    ]
)
abstract class LocalModule {

    @Binds
    internal abstract fun bindWeatherLocalLoader(
        loader: WeatherForecastLocalLoaderImp): WeatherForecastLocalLoader

    @Binds
    internal abstract fun bindWeatherLocalSaver(
        saver: WeatherForecastLocalSaverImp): WeatherForecastLocalSaver
}