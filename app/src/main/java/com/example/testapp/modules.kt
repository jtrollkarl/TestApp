package com.example.testapp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindVMFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}

@Module(includes = [CacheModule::class])
class RetrofitModule {

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