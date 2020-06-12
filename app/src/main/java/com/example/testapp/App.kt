package com.example.testapp

import android.app.Activity
import android.app.Application
import timber.log.Timber

class App : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        fun getApp(activity: Activity): App = activity.application as App
    }
}