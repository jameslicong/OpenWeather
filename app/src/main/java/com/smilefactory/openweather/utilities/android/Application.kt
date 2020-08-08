package com.smilefactory.openweather.utilities.android

import com.smilefactory.openweather.utilities.android.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class Application : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().create(this)
    }

    override fun onCreate() {
        super.onCreate()

        initializeTimber()
    }

    private fun initializeTimber() {
        Timber.plant(Timber.DebugTree())
    }
}