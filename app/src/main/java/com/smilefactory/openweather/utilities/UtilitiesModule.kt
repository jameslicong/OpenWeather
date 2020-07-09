package com.smilefactory.openweather.utilities

import com.smilefactory.openweather.utilities.framework.FrameworkModule
import com.smilefactory.openweather.utilities.rx.RxModule
import dagger.Module

@Module(
    includes = [
        FrameworkModule::class,
        RxModule::class
    ]
)
abstract class UtilitiesModule {

}