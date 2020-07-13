package com.smilefactory.openweather.screens.list

import com.smilefactory.openweather.utilities.dagger.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ListModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun weatherForecastListFragment(): WeatherForecastListFragment
}