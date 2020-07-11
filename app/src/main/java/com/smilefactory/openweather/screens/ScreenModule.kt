package com.smilefactory.openweather.screens

import com.smilefactory.openweather.screens.details.DetailsModule
import com.smilefactory.openweather.screens.list.ListModule
import com.smilefactory.openweather.utilities.dagger.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(
    includes = [
        DetailsModule::class,
        ListModule::class
    ]
)
abstract class ScreenModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}