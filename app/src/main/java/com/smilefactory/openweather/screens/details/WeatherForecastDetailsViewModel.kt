package com.smilefactory.openweather.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilefactory.openweather.repository.WeatherForecastLoader
import com.smilefactory.openweather.repository.WeatherForecastUpdater
import com.smilefactory.openweather.repository.model.WeatherForecast
import com.smilefactory.openweather.utilities.framework.BaseViewModel
import com.smilefactory.openweather.utilities.rx.RxSchedulerUtils
import javax.inject.Inject

class WeatherForecastDetailsViewModel
@Inject constructor(private val loader: WeatherForecastLoader,
                    private val updater: WeatherForecastUpdater,
                    private val schedulerUtils: RxSchedulerUtils) : BaseViewModel() {

    private val weatherForecastLiveData = MutableLiveData<WeatherForecast>()

    fun loadDetails(cityName: String) {
        addDisposable(
            loader.details(cityName)
                .compose(schedulerUtils.forSingle())
                .subscribe(
                    { weatherForecast ->
                        weatherForecastLiveData.postValue(weatherForecast) },
                    { it.printStackTrace() }
                )
        )
    }

    fun setAsFavorite(isFavorite: Boolean, cityName: String) {
        addDisposable(
            updater.setFavorite(isFavorite, cityName)
                .andThen(loader.details(cityName))
                .compose(schedulerUtils.forSingle())
                .subscribe(
                    { weatherForecast ->
                        weatherForecastLiveData.postValue(weatherForecast) },
                    { it.printStackTrace() }
                )
        )
    }

    fun weatherForecastLiveData(): LiveData<WeatherForecast> {
        return weatherForecastLiveData
    }

}