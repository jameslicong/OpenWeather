package com.smilefactory.openweather.repository.remote

import com.smilefactory.openweather.BuildConfig
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.core.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ApiFactoryImp
    @Inject
    internal constructor() : ApiFactory {

    override fun <T> create(apiClass: Class<T>): Single<T> {
        return retrofit()
            .map { retrofit -> retrofit.create(apiClass) }
    }

    private fun intoRetrofit(client: OkHttpClient): Single<Retrofit> {
        return Single.fromCallable {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
        }
    }

    private fun client(): Single<OkHttpClient> {
        return Single.fromCallable { OkHttpClient.Builder() }
            .flatMap { addLoggingInterceptor(it) }
            .map { it.build() }

    }

    private fun addLoggingInterceptor(
        builder: OkHttpClient.Builder): Single<OkHttpClient.Builder> {
        return Single.fromCallable { builder }
            .map {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                it.addInterceptor(loggingInterceptor)
                it.readTimeout(60, TimeUnit.SECONDS)
                it
            }
    }

    private fun retrofit(): Single<Retrofit> {
        return client().flatMap { intoRetrofit(it) }
    }
}