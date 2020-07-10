package com.smilefactory.openweather.repository.remote.api

import com.smilefactory.openweather.BuildConfig
import com.smilefactory.openweather.repository.model.WeatherForecast
import com.smilefactory.openweather.repository.model.WeatherForecastList
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("group")
    fun getList(
        @Query("id") cities: String = "1701668,3067696,1835848", // Manila, Prague and Soul
        @Query("appid") apiKey: String = BuildConfig.API_KEY,
        @Query("units") units: String = "metric"
    ): Single<WeatherForecastList>

    @GET("weather")
    fun getDetails(
        @Query("q") city: String,
        @Query("appid") apiKey: String = BuildConfig.API_KEY
    ): Single<WeatherForecast>
}