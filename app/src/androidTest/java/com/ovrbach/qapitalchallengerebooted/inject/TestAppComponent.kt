package com.ovrbach.qapitalchallengerebooted.inject

import android.app.Application
import com.ovrbach.qapitalchallengerebooted.domain.repository.SavedGoalsRepository
import com.ovrbach.qapitalchallengerebooted.inject.module.*
import com.ovrbach.qapitalchallengerebooted.test.TestApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        TestAppModule::class,
        TestDataModule::class,
        TestLocalModule::class,
        TestRemoteModule::class,
        PresentationModule::class,
        UiModule::class
    ]
)
interface TestAppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): TestAppComponent
    }

    fun inject(app: TestApp)

    fun goalsRepository(): SavedGoalsRepository
}