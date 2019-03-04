package com.ovrbach.qapitalchallengerebooted.test

import android.app.Activity
import android.app.Application
import androidx.test.core.app.ApplicationProvider
import com.ovrbach.qapitalchallengerebooted.inject.DaggerTestAppComponent
import com.ovrbach.qapitalchallengerebooted.inject.TestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TestApp : Application(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>
    private lateinit var appComponent: TestAppComponent

    companion object {
        fun appComponent(): TestAppComponent {
            return ApplicationProvider
                .getApplicationContext<TestApp>()
                .appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerTestAppComponent
            .builder()
            .application(this)
            .build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }

}