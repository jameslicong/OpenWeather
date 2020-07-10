package com.smilefactory.openweather.utilities.android

import com.smilefactory.openweather.Application
import com.smilefactory.openweather.repository.RepositoryModule
import com.smilefactory.openweather.utilities.UtilitiesModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidModule::class,
        RepositoryModule::class,
        UtilitiesModule::class
    ]
)
@Singleton
interface ApplicationComponent : AndroidInjector<Application> {

    @Component.Builder
    abstract class ApplicationComponentBuilder :AndroidInjector.Builder<Application>()
}