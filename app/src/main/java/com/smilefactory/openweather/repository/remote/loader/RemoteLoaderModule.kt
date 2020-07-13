package com.smilefactory.openweather.repository.remote.loader

import dagger.Binds
import dagger.Module

@Module
abstract class RemoteLoaderModule {

    @Binds
    internal abstract fun bindWeatherForecastRemoteLoader(
        remoteLoader: WeatherForecastRemoteLoaderImp
    ): WeatherForecastRemoteLoader
}