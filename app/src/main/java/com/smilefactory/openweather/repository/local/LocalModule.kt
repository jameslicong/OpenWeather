package com.smilefactory.openweather.repository.local

import com.smilefactory.openweather.repository.local.database.DatabaseModule
import dagger.Module

@Module(
    includes = [
        DatabaseModule::class
    ]
)
abstract class LocalModule { }