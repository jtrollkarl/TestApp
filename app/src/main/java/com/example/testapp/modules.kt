package com.example.testapp

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.api.WeatherApi
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Singleton

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindVMFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}

@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    @Singleton
    fun provideWeatherApi(retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)
}

@Module(includes = [CacheModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(moshi: MoshiConverterFactory, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://www.yr.no/api/v0")
            .addConverterFactory(moshi)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient =
        OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .cache(cache)
            .build()

    @Provides
    @Singleton
    fun provideInterceptor() = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
        override fun log(message: String) {
            Timber.d(message)
        }
    }).apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    @Provides
    @Singleton
    fun provideMoshi(): MoshiConverterFactory = MoshiConverterFactory.create(
        Moshi.Builder().build()
    )
}

@Module(includes = [ContextModule::class])
class CacheModule {

    @Provides
    @Singleton
    fun provideCache(context: Context): Cache = Cache(context.cacheDir, 5 * 5 * 1024)
}

@Module
class ContextModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context
}