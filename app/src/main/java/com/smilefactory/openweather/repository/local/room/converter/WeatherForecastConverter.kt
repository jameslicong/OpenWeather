package com.smilefactory.openweather.repository.local.room.converter

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.smilefactory.openweather.repository.model.*
import com.smilefactory.openweather.utilities.ConverterUtil

class WeatherForecastConverter {

    @TypeConverter
    fun fromCoord(coord: Coord): String {
        return ConverterUtil.serialise(coord)
    }

    @TypeConverter
    fun fromSys(sys: Sys): String {
        return ConverterUtil.serialise(sys)
    }

    @TypeConverter
    fun fromWeatherList(weather: ArrayList<Weather>): String {
        return ConverterUtil.serialise(weather)
    }

    @TypeConverter
    fun fromMain(main: Main): String {
        return ConverterUtil.serialise(main)
    }

    @TypeConverter
    fun fromWind(wind: Wind): String {
        return ConverterUtil.serialise(wind)
    }

    @TypeConverter
    fun fromClouds(clouds: Clouds): String {
        return ConverterUtil.serialise(clouds)
    }

    @TypeConverter
    fun toCoord(serialized: String): Coord {
        return ConverterUtil.deserialise(serialized, Coord::class.java)
    }

    @TypeConverter
    fun toSys(serialized: String): Sys {
        return ConverterUtil.deserialise(serialized, Sys::class.java)
    }

    @TypeConverter
    fun toWeatherList(serialized: String): ArrayList<Weather> {
        return ConverterUtil.deserialise(serialized,
            object : TypeToken<ArrayList<Weather>>() {}.type)
    }

    @TypeConverter
    fun toMain(serialized: String): Main {
        return ConverterUtil.deserialise(serialized, Main::class.java)
    }

    @TypeConverter
    fun toWind(serialized: String): Wind {
        return ConverterUtil.deserialise(serialized, Wind::class.java)
    }

    @TypeConverter
    fun toClouds(serialized: String): Clouds {
        return ConverterUtil.deserialise(serialized, Clouds::class.java)
    }
}