package com.smilefactory.openweather.factory

import com.smilefactory.openweather.repository.model.*
import org.fluttercode.datafactory.impl.DataFactory

object WeatherForecastFactory {

    private val FACTORY = DataFactory()

    fun createWeatherForecast(): WeatherForecast {
        return WeatherForecast(
            id = FACTORY.getNumberBetween(1000000, 9999999).toLong(),
            coord = Coord(
                lon = FACTORY.getNumberBetween(10, 99).toDouble(),
                lat = FACTORY.getNumberBetween(10, 99).toDouble()),
            sys = Sys(
                country = FACTORY.streetName,
                timezone = FACTORY.getNumberBetween(10000, 99999).toLong(),
                sunrise = FACTORY.getNumberBetween(100000000, 999999999).toLong(),
                sunset = FACTORY.getNumberBetween(100000000, 999999999).toLong()),
            weather = arrayListOf(Weather(
                id = FACTORY.getNumberBetween(100, 999).toLong(),
                main = FACTORY.randomWord,
                description = FACTORY.randomWord,
                icon = FACTORY.getRandomChars(3).toString())),
            main = Main(
                temp = FACTORY.getNumberBetween(10, 99).toDouble(),
                feelsLike = FACTORY.getNumberBetween(10, 99).toDouble(),
                tempMin = FACTORY.getNumberBetween(10, 99).toDouble(),
                tempMax = FACTORY.getNumberBetween(10, 99).toDouble(),
                pressure = FACTORY.getNumberBetween(1000, 9999),
                humidity = FACTORY.getNumberBetween(10, 100)),
            visibility = FACTORY.getNumberBetween(10000, 99999).toLong(),
            wind = Wind(
                speed = FACTORY.getNumberBetween(1, 10).toFloat(),
                deg = FACTORY.getNumberBetween(100, 999).toLong()),
            clouds = Clouds(all = FACTORY.getNumberBetween(10, 99)),
            dt = FACTORY.getNumberBetween(100000000, 999999999).toLong(),
            name = FACTORY.name,
            isFavorite = false)
    }

    fun weatherForecastList(size: Int): WeatherForecastList {
        var list = mutableListOf<WeatherForecast>()
        for (index in 0 until size) {
            list.add(createWeatherForecast())
        }
        return WeatherForecastList(cnt = size, list = list)
    }
}