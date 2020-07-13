package com.smilefactory.openweather.repository.local.loader

import com.smilefactory.openweather.factory.WeatherForecastFactory.weatherForecastList
import com.smilefactory.openweather.repository.local.room.dao.WeatherForecastDao
import com.smilefactory.openweather.repository.model.WeatherForecastList
import com.smilefactory.openweather.utilities.RoomTestRule
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
class WeatherForecastLocalLoaderImpTest {

    @Rule
    @JvmField
    val rule = RoomTestRule()

    private lateinit var classUnderTest: WeatherForecastLocalLoaderImp

    private lateinit var dao: WeatherForecastDao

    @Before
    fun setup() {
        dao = rule.getDatabase().weatherForecastDao()
        classUnderTest = WeatherForecastLocalLoaderImp(dao)
    }

    @Test
    fun `load all items in database`() {
        val listSize = 3
        val generatedList = weatherForecastList(listSize)
        
        val expectedList = WeatherForecastList(
            cnt = listSize,
            list = generatedList.list.sortedWith(compareBy {it.id})
        )

        expectedList.list.forEach { item ->
            dao.insert(item)
        }

        val actualResult = classUnderTest.all().blockingGet()

        assertEquals(actualResult.size, expectedList.list.size)
        for (index in 0 until listSize) {
            assertEquals(actualResult[index].id, expectedList.list[index].id)
            assertEquals(actualResult[index].name, expectedList.list[index].name)
        }
    }

    @Test
    fun `load by city name`() {
        val listSize = 3
        val generatedList = weatherForecastList(listSize)

        generatedList.list.forEach { item ->
            dao.insert(item)
        }

        val expectedResult = generatedList.list[Random.nextInt(listSize)]

        val actualResult = classUnderTest.byName(expectedResult.name).blockingGet()

        assertEquals(actualResult.id, expectedResult.id)
        assertEquals(actualResult.name, expectedResult.name)
    }
}
