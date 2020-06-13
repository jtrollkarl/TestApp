package com.example.testapp

import com.example.testapp.ui.FragmentSearch
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(fragment: FragmentSearch)

}