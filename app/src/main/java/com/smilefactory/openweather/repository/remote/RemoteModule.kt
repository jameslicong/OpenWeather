package com.smilefactory.openweather.repository.remote

import dagger.Binds
import dagger.Module

@Module
abstract class RemoteModule {

    @Binds
    internal abstract fun bindApiFactory(apiFactory: ApiFactoryImp): ApiFactory
}