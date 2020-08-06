package com.smilefactory.openweather.screens.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.smilefactory.openweather.factory.WeatherForecastFactory.weatherForecastList
import com.smilefactory.openweather.repository.WeatherForecastLoader
import com.smilefactory.openweather.repository.model.WeatherForecast
import com.smilefactory.openweather.utilities.rx.RxImmediateSchedulerRule
import com.smilefactory.openweather.utilities.rx.SynchronousRxSchedulerUtils
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.given
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Spy

class WeatherForecastListViewModelTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    var ruleForLiveData = InstantTaskExecutorRule()

    @Mock
    private lateinit var loader: WeatherForecastLoader

    @Spy
    private lateinit var scheduler: SynchronousRxSchedulerUtils

    @Mock
    private lateinit var mockWeatherForecastListObserver: Observer<List<WeatherForecast>>

    private lateinit var viewModel: WeatherForecastListViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = WeatherForecastListViewModel(loader, scheduler)
    }

    @Test
    fun `Successful load of items`() {

        var weatherForecast = weatherForecastList(3)
        var expectedResult = weatherForecast.list

        viewModel.weatherForecastListLiveData().observeForever(mockWeatherForecastListObserver)

        given(loader.all()).willReturn(Single.just(expectedResult))

        viewModel.loadItems()

        verify(mockWeatherForecastListObserver, times(1)).onChanged(expectedResult)
        verify(loader).all()
        verify(scheduler).forSingle<List<WeatherForecast>>()
        verifyNoMoreInteractions(loader, scheduler)
    }
}