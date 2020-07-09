package com.smilefactory.openweather.utilities.dagger

import dagger.MapKey
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val viewModelClass: KClass<out ViewModelKey>)