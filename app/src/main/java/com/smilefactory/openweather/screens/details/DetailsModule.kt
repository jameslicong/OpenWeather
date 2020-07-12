package com.smilefactory.openweather.screens.details

import com.smilefactory.openweather.utilities.dagger.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class DetailsModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun weatherForecastDetailsFragment(): WeatherForecastDetailsFragment
}