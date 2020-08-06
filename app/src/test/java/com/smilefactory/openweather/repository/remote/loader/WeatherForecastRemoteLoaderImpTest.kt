package com.smilefactory.openweather.repository.remote.loader

import com.smilefactory.openweather.factory.WeatherForecastFactory.createWeatherForecast
import com.smilefactory.openweather.factory.WeatherForecastFactory.weatherForecastList
import com.smilefactory.openweather.repository.remote.api.ApiFactory
import com.smilefactory.openweather.repository.remote.api.WeatherApi
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Assert.assertSame
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.MockitoAnnotations.openMocks
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WeatherForecastRemoteLoaderImpTest  {

    @InjectMocks
    private lateinit var classUnderTest: WeatherForecastRemoteLoaderImp

    @Mock
    private lateinit var apiFactory: ApiFactory
    @Mock
    private lateinit var weatherApi: WeatherApi

    @Before
    fun setup() {
        openMocks(this)
        classUnderTest = WeatherForecastRemoteLoaderImp(apiFactory)
    }

    @Test
    fun `get list from remote`() {
        val listSize = 3
        val expectedResultInWeatherForecastList = weatherForecastList(listSize)
        val expectedResult = expectedResultInWeatherForecastList.list

        given(apiFactory.create(WeatherApi::class.java)).willReturn(Single.just(weatherApi))
        given(weatherApi.getList())
            .willReturn(Single.just(expectedResultInWeatherForecastList))

        val actualResult = classUnderTest.list().test()

        verify(apiFactory).create(WeatherApi::class.java)
        verify(weatherApi).getList()
        verifyNoMoreInteractions(apiFactory, weatherApi)

        assertEquals(actualResult.values()[0].size, listSize)
        for (index in 0 until listSize) {
            assertSame(actualResult.values()[0][index], expectedResult[index])
        }
    }

    @Test
    fun `search weather details`() {
        val expectedResult = createWeatherForecast()
        val cityName = expectedResult.name

        given(apiFactory.create(WeatherApi::class.java)).willReturn(Single.just(weatherApi))
        given(weatherApi.getDetails(city = cityName))
            .willReturn(Single.just(expectedResult))

        val actualResult =
            classUnderTest.details(cityName).test()

        verify(apiFactory).create(WeatherApi::class.java)
        verify(weatherApi).getDetails(cityName)
        verifyNoMoreInteractions(apiFactory, weatherApi)

        assertSame(actualResult.values()[0], expectedResult)
    }
}
