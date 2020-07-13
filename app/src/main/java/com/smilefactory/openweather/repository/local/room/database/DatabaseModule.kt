package com.smilefactory.openweather.repository.local.room.database

import androidx.room.Room
import com.smilefactory.openweather.Application
import com.smilefactory.openweather.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideSqliteDatabase(application: Application): Database {
        return Room.databaseBuilder(
            application,
            Database::class.java,
            BuildConfig.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}