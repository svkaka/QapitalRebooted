package com.ovrbach.qapitalchallengerebooted.inject.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ovrbach.qapitalchallengerebooted.inject.ViewModelFactory
import com.ovrbach.qapitalchallengerebooted.presentation.GoalsViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(GoalsViewModel::class)
    abstract fun bindGoalsViewModel(viewModel: GoalsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}


@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)