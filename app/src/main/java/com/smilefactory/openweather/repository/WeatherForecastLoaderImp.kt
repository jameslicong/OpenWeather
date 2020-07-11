package com.smilefactory.openweather.repository

import com.smilefactory.openweather.repository.local.loader.WeatherForecastLocalLoader
import com.smilefactory.openweather.repository.local.saver.WeatherForecastLocalSaver
import com.smilefactory.openweather.repository.model.WeatherForecast
import com.smilefactory.openweather.repository.remote.loader.WeatherForecastRemoteLoader
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class WeatherForecastLoaderImp
@Inject constructor(
    private val localLoader: WeatherForecastLocalLoader,
    private val localSaver: WeatherForecastLocalSaver,
    private val remoteLoader: WeatherForecastRemoteLoader
) : WeatherForecastLoader {

    override fun all(): Single<List<WeatherForecast>> {
        return remoteLoader.list()
            .flatMapObservable { items -> Observable.fromIterable(items) }
            .flatMapCompletable { item -> saveOrUpdate(item) }
            .andThen(localLoader.all())

    }

    private fun saveOrUpdate(weatherForecase: WeatherForecast): Completable {
        return localLoader.byName(weatherForecase.name)
            .map {
                it.sys = weatherForecase.sys
                it.weather = weatherForecase.weather
                it.main = weatherForecase.main
                it.visibility = weatherForecase.visibility
                it.wind = weatherForecase.wind
                it.clouds = weatherForecase.clouds
                it.dt = weatherForecase.dt
                it
            }
            .onErrorResumeNext { Single.just(weatherForecase) }
            .flatMapCompletable { item -> localSaver.add(item) }
    }
}