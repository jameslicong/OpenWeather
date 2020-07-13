package com.smilefactory.openweather.utilities.rx

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RxModule {

    companion object {
        @Provides
        @Singleton
        internal fun provideRxSchedulerUtils(): RxSchedulerUtils {
            return RxSchedulerUtilsImp()
        }
    }
}