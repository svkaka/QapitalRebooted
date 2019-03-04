package com.ovrbach.qapitalchallengerebooted.inject

import android.app.Application
import com.ovrbach.qapitalchallengerebooted.MyApplication
import com.ovrbach.qapitalchallengerebooted.inject.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApplicationModule::class,
        DataModule::class,
        LocalModule::class,
        PresentationModule::class,
        RemoteModule::class,
        UiModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MyApplication)
}