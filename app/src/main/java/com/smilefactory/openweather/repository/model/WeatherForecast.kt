package com.smilefactory.openweather.repository.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = WeatherForecast.TABLE_NAME)
data class WeatherForecast(

    @PrimaryKey
    var id: Long,
    var coord: Coord,
    var sys: Sys,
    var weather: ArrayList<Weather>,
    var main: Main,
    var visibility: Long,
    var wind: Wind,
    var clouds: Clouds,
    var dt: Long,
    var name: String,
    var isFavorite: Boolean = false

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

