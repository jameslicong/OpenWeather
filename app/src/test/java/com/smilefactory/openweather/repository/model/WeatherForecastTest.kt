package com.smilefactory.openweather.repository.model

import com.smilefactory.openweather.factory.WeatherForecastFactory.createWeatherForecast
import org.junit.Assert.assertNotNull
import org.junit.Test


class WeatherForecastTest {

    @Test
    fun `weather forecast model`() {

        val weatherForecast = createWeatherForecast()

        assertNotNull(weatherForecast.id)
        assertNotNull(weatherForecast.coord)
        assertNotNull(weatherForecast.sys)
        assertNotNull(weatherForecast.weather)
        assertNotNull(weatherForecast.main)
        assertNotNull(weatherForecast.visibility)
        assertNotNull(weatherForecast.wind)
        assertNotNull(weatherForecast.clouds)
        assertNotNull(weatherForecast.dt)
        assertNotNull(weatherForecast.name)
        assertNotNull(weatherForecast.isFavorite)
    }
}