package com.smilefactory.openweather.repository.local.dao

import androidx.room.*
import com.smilefactory.openweather.repository.model.WeatherForecast
import com.smilefactory.openweather.repository.model.WeatherForecast.Companion.TABLE_NAME
import io.reactivex.rxjava3.core.Single

@Dao
interface WeatherForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(weatherForecast: WeatherForecast)

}