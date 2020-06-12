package com.example.testapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.MapKey
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton
import kotlin.reflect.KClass

//a custom viewmodel factory which will be used to provide viewmodels with x number of constructor dependencies
/*
Basically the way this works is by taking advantage of multibindings with @IntoMap. We defined a custom Key earlier (ViewModelKey), which
is any KClass that extends from ViewModel. We then label any ViewModel (in our ViewModelModule) binding with @ViewModelKey
with the parameter being the class name (KClass).
 */
@Singleton
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}

//we define our own custom Map key here, which is any (Kotlin) Class that extends from viewmodel.
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)