package com.example.testapp

import com.example.testapp.ui.FragmentSearchResults
import com.example.testapp.ui.FragmentWeather
import com.example.testapp.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(fragment: FragmentSearchResults)

    fun inject(fragment: FragmentWeather)

    fun inject(activity: MainActivity)
}