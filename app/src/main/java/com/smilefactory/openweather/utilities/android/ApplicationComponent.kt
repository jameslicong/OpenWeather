package com.smilefactory.openweather.utilities.android

import com.smilefactory.openweather.Application
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidModule::class
    ]
)
@Singleton
interface ApplicationComponent : AndroidInjector<Application> {

    @Component.Builder
    abstract class ApplicationComponentBuilder :AndroidInjector.Builder<Application>()
}