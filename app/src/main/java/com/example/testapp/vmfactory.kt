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
with the parameter being the class name (KClass). When the app builds (kapt), all the ViewModels are added to a map. Each corresponding
to a class key which we define on the binding with @ViewModelKey. Dagger knows which dependencies to grab as we mark the ViewModels constructor with
@Inject. We also type the ViewModel Key as a Provider, so a new instance is created only when get is called. Not marking it as Provider would have it already
be instantiated.
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