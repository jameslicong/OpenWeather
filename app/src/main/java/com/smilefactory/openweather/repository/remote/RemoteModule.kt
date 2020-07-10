package com.smilefactory.openweather.repository.remote

import com.smilefactory.openweather.repository.remote.api.ApiModule
import com.smilefactory.openweather.repository.remote.loader.RemoteLoaderModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        ApiModule::class,
        RemoteLoaderModule::class
    ]
)
abstract class RemoteModule {

    @Binds
    internal abstract fun bindApiFactory(apiFactory: ApiFactoryImp): ApiFactory
}