package com.smilefactory.openweather.utilities.android

import android.content.Context
import com.smilefactory.openweather.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }
}