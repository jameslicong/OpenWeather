package com.smilefactory.openweather.repository

import com.smilefactory.openweather.factory.WeatherForecastFactory.createWeatherForecast
import com.smilefactory.openweather.repository.local.saver.WeatherForecastLocalSaver
import io.reactivex.Completable
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
class WeatherForecastUpdaterImpTest {

    @InjectMocks
    private lateinit var classUnderTest: WeatherForecastUpdaterImp

    @Mock
    private lateinit var localSaver: WeatherForecastLocalSaver

    @Before
    fun setup() {
        openMocks(this)
        classUnderTest = WeatherForecastUpdaterImp(localSaver)
    }

    @Test
    fun `save as favorite`() {

        val weatherForecast = createWeatherForecast()

        given(localSaver.asFavorite(true, weatherForecast.name))
            .willReturn(Completable.complete())

        classUnderTest
            .setFavorite(true, weatherForecast.name)
            .test()
            .await()
            .assertComplete()

        verify(localSaver).asFavorite(true, weatherForecast.name)
        verifyNoMoreInteractions(localSaver)
    }
}