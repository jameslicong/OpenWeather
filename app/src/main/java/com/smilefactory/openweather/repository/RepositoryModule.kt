package com.smilefactory.openweather.repository

import com.smilefactory.openweather.repository.remote.RemoteModule
import dagger.Module

@Module(
    includes = [
        RemoteModule::class
    ]
)
abstract class RepositoryModule {}