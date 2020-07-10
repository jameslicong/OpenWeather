package com.smilefactory.openweather.repository.model

data class WeatherForecastList(
    val cnt: Int,
    val list: List<WeatherForecast>
)