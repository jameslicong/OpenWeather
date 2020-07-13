package com.smilefactory.openweather.repository.remote

import com.smilefactory.openweather.repository.remote.api.ApiFactory
import com.smilefactory.openweather.repository.remote.api.ApiFactoryImp
import com.smilefactory.openweather.repository.remote.loader.RemoteLoaderModule
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        RemoteLoaderModule::class
    ]
)
abstract class RemoteModule {

    @Binds
    internal abstract fun bindApiFactory(apiFactory: ApiFactoryImp): ApiFactory
}