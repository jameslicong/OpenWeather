package com.smilefactory.openweather.repository.local.room.dao

import androidx.room.*
import com.smilefactory.openweather.repository.model.WeatherForecast
import com.smilefactory.openweather.repository.model.WeatherForecast.Companion.TABLE_NAME
import io.reactivex.Single

@Dao
interface WeatherForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherForecast: WeatherForecast)

    @Query("SELECT * FROM $TABLE_NAME")
    fun all(): Single<List<WeatherForecast>>

    @Query("SELECT * FROM $TABLE_NAME WHERE name =:name")
    fun byName(name: String): Single<WeatherForecast>

}