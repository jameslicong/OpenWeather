package com.smilefactory.openweather.utilities

import com.smilefactory.openweather.utilities.framework.FrameworkModule
import dagger.Module

@Module(
    includes = [
        FrameworkModule::class
    ]
)
abstract class UtilitiesModule {

}