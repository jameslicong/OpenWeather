package com.smilefactory.openweather.repository

import com.smilefactory.openweather.repository.local.LocalModule
import com.smilefactory.openweather.repository.remote.RemoteModule
import dagger.Module

@Module(
    includes = [
        LocalModule::class,
        RemoteModule::class
    ]
)
abstract class RepositoryModule {}