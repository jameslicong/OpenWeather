package com.smilefactory.openweather.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smilefactory.openweather.repository.WeatherForecastLoader
import com.smilefactory.openweather.repository.model.WeatherForecast
import com.smilefactory.openweather.utilities.framework.BaseViewModel
import com.smilefactory.openweather.utilities.rx.RxSchedulerUtils
import javax.inject.Inject

class WeatherForecastListViewModel
@Inject
constructor(private val loader: WeatherForecastLoader,
            private val schedulers: RxSchedulerUtils) : BaseViewModel() {

    private val weatherForecastListLiveData = MutableLiveData<List<WeatherForecast>>()

    private val weatherForecastRequestErrorLiveData = MutableLiveData<String>()

    fun loadItems() {
        addDisposable(
            loader.all()
                .compose(schedulers.forSingle())
                .subscribe(
                    { items -> weatherForecastListLiveData.postValue(items) },
                    { error ->
                        weatherForecastRequestErrorLiveData.postValue(error.message) }
                )
        )
    }

    fun weatherForecastListLiveData(): LiveData<List<WeatherForecast>> {
        return weatherForecastListLiveData
    }

    fun weatherForecastErrorLiveData(): LiveData<String> {
        return weatherForecastRequestErrorLiveData
    }


}