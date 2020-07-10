package com.smilefactory.openweather.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.smilefactory.openweather.repository.model.WeatherForecast.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class WeatherForecast(

    @PrimaryKey
    val id: Long,
    val coord: Coord,
    val sys: Sys,
    val weather: List<Weather>,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val name: String,
    val isFavorite: Boolean = false

) {
    companion object {
        const val TABLE_NAME = "WeatherForecast"
    }
}

data class Coord (
    val lon: Double,
    val lat: Double
)

data class Sys (
    val country: String,
    val timezone: Long,
    val sunrise: Long,
    val sunset: Long
)

data class Weather (
    val id: Long,
    val main: String,
    val description: String,
    val icon: String
)

data class Main (
    val temp: Double,

    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("temp_min")
    val tempMin: Double,

    @SerializedName("temp_max")
    val tempMax: Double,
    val pressure: Int,
    val humidity: Int
)

data class Wind (
    val speed: Float,
    val deg: Long
)

data class Clouds (
    val all: Int
)

