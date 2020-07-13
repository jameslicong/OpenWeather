package com.smilefactory.openweather.repository.local.saver

import com.smilefactory.openweather.factory.WeatherForecastFactory
import com.smilefactory.openweather.factory.WeatherForecastFactory.createWeatherForecast
import com.smilefactory.openweather.repository.local.room.dao.WeatherForecastDao
import com.smilefactory.openweather.utilities.RoomTestRule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import kotlin.random.Random

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class WeatherForecastLocalSaverImpTest {

    @Rule
    @JvmField
    val rule = RoomTestRule()

    private lateinit var classUnderTest: WeatherForecastLocalSaverImp

    private lateinit var dao: WeatherForecastDao

    @Before
    fun setup() {
        dao = rule.getDatabase().weatherForecastDao()
        classUnderTest = WeatherForecastLocalSaverImp(dao)
    }

    @Test
    fun `add items in the database`() {

        val expected = createWeatherForecast()

        classUnderTest.add(expected).test().await().assertComplete()

        dao.byName(expected.name)
            .doOnSuccess { assertEquals(it.name, expected.name) }
            .subscribe()
    }

    @Test
    fun `set item as favorite`() {
        val listSize = 3
        val generatedList = WeatherForecastFactory.weatherForecastList(listSize)

        generatedList.list.forEach { item ->
            dao.insert(item)
        }

        val expected = generatedList.list[Random.nextInt(listSize)]

        classUnderTest.asFavorite(true, expected.name).test().await().assertComplete()

        dao.byName(expected.name)
            .doOnSuccess {
                assertEquals(it.name, expected.name)
                assertTrue(it.isFavorite)
            }
            .subscribe()


    }
}