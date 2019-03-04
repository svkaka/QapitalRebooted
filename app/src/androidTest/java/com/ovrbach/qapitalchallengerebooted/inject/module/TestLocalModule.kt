package com.ovrbach.qapitalchallengerebooted.inject.module

import android.app.Application
import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsLocal
import com.ovrbach.qapitalchallengerebooted.local.AppDatabase
import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock


@Module
object TestLocalModule {

    @Provides
    @JvmStatic
    fun provideDatabase(application: Application): AppDatabase {
        return AppDatabase.getInstance(application)
    }

    @Provides
    @JvmStatic
    fun provideGoalsLocal(): GoalsLocal {
        return mock(GoalsLocal::class.java)
    }
}