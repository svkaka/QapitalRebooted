package com.ovrbach.qapitalchallengerebooted.inject.module

import android.app.Application
import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsLocal
import com.ovrbach.qapitalchallengerebooted.local.AppDatabase
import com.ovrbach.qapitalchallengerebooted.local.GoalsLocalImpl
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
abstract class LocalModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideDatabase(application: Application): AppDatabase {
            return AppDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindGoalsLocal(goalsLocal: GoalsLocalImpl): GoalsLocal
}