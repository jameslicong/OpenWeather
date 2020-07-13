package com.smilefactory.openweather.utilities

import android.content.Context
import androidx.room.Room.databaseBuilder
import androidx.test.core.app.ApplicationProvider
import com.smilefactory.openweather.repository.local.room.database.Database
import org.junit.rules.ExternalResource
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

class RoomTestRule : ExternalResource() {

    private val RUNNER_NOT_ROBOLECTRIC = (
            "All tests that use " + RoomTestRule::class.java.simpleName + " must use "
                    + RobolectricTestRunner::class.java.simpleName + " as the test runner via "
                    + RunWith::class.java.simpleName)

    private lateinit var database: Database

    private lateinit var context: Context

    override fun before() {
        super.before()

        context = ApplicationProvider.getApplicationContext()
            ?: throw RuntimeException(RUNNER_NOT_ROBOLECTRIC)


        database =
            databaseBuilder(context, Database::class.java, "test-database")
                .allowMainThreadQueries()
                .build()

    }

    fun getDatabase(): Database {
        return database
    }
}