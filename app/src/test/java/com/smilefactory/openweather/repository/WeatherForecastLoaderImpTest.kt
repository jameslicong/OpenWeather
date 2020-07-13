package com.smilefactory.openweather.repository

import com.smilefactory.openweather.factory.WeatherForecastFactory
import com.smilefactory.openweather.factory.WeatherForecastFactory.createWeatherForecast
import com.smilefactory.openweather.repository.local.loader.WeatherForecastLocalLoader
import com.smilefactory.openweather.repository.local.saver.WeatherForecastLocalSaver
import com.smilefactory.openweather.repository.model.WeatherForecastList
import com.smilefactory.openweather.repository.remote.loader.WeatherForecastRemoteLoader
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class WeatherForecastLoaderImpTest {

    @InjectMocks
    private lateinit var classUnderTest: WeatherForecastLoaderImp

    @Mock
    private lateinit var localLoader: WeatherForecastLocalLoader

    @Mock
    private lateinit var localSaver: WeatherForecastLocalSaver

    @Mock
    private lateinit var remoteLoader: WeatherForecastRemoteLoader

    @Before
    fun setup() {
        initMocks(this)
        classUnderTest = WeatherForecastLoaderImp(
            localLoader = localLoader,
            localSaver =  localSaver,
            remoteLoader = remoteLoader)
    }

    @Test
    fun `load all`() {
        val listSize = 3
        val generatedList = WeatherForecastFactory.weatherForecastList(listSize)

        val expectedWeatherForecastList = WeatherForecastList(
            cnt = listSize,
            list = generatedList.list.sortedWith(compareBy {it.id})
        )

        given(remoteLoader.list()).willReturn(Single.just(expectedWeatherForecastList.list))
        expectedWeatherForecastList.list.forEach { item ->
            given(localLoader.byName(item.name)).willReturn(Single.just(item))
            given(localSaver.add(item)).willReturn(Completable.complete())
        }
        given(localLoader.all()).willReturn(Single.just(expectedWeatherForecastList.list))

        val actualResult = classUnderTest.all().blockingGet()

        verify(remoteLoader).list()
        expectedWeatherForecastList.list.forEach { item ->
            verify(localLoader).byName(item.name)
            verify(localSaver).add(item)
        }
        verify(localLoader).all()
        verifyNoMoreInteractions(remoteLoader, localSaver, localLoader)

        assertEquals(actualResult.size, expectedWeatherForecastList.list.size)
        for (index in 0 until listSize) {
            assertEquals(actualResult[index].id, expectedWeatherForecastList.list[index].id)
            assertEquals(actualResult[index].name, expectedWeatherForecastList.list[index].name)
        }

    }

    @Test
    fun `load details`() {
        val expectedResult = createWeatherForecast()

        given(remoteLoader.details(expectedResult.name)).willReturn(Single.just(expectedResult))
        given(localLoader.byName(expectedResult.name)).willReturn(Single.just(expectedResult))
        given(localSaver.add(expectedResult)).willReturn(Completable.complete())

        val actualResult = classUnderTest.details(expectedResult.name).blockingGet()

        verify(remoteLoader).details(expectedResult.name)
        verify(localLoader, times(2)).byName(expectedResult.name)
        verify(localSaver).add(expectedResult)
        verifyNoMoreInteractions(remoteLoader, localSaver, localLoader)

        assertEquals(actualResult.id, expectedResult.id)
        assertEquals(actualResult.name, expectedResult.name)
    }

    @Test
    fun `load local details`() {
        val expectedResult = createWeatherForecast()

        given(localLoader.byName(expectedResult.name)).willReturn(Single.just(expectedResult))

        val actualResult =
            classUnderTest.localDetails(expectedResult.name).blockingGet()

        verify(localLoader).byName(expectedResult.name)
        verifyNoMoreInteractions(localLoader)

        assertEquals(actualResult.id, expectedResult.id)
        assertEquals(actualResult.name, expectedResult.name)
    }
}