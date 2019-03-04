package com.ovrbach.qapitalchallengerebooted.inject.module

import com.ovrbach.qapitalchallengerebooted.feature.goals.GoalsActivity
import com.ovrbach.qapitalchallengerebooted.UiThread
import com.ovrbach.qapitalchallengerebooted.domain.executor.PostExecutionThread
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesGoalsActivity(): GoalsActivity

}
