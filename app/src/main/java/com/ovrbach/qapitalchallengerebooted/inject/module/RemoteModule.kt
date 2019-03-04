package com.ovrbach.qapitalchallengerebooted.inject.module

import com.ovrbach.qapitalchallengerebooted.BuildConfig
import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsRemote
import com.ovrbach.qapitalchallengerebooted.remote.GoalsRemoteImpl
import com.ovrbach.qapitalchallengerebooted.remote.service.GoalsService
import com.ovrbach.qapitalchallengerebooted.remote.service.GoalsServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        fun provideGoalsService(): GoalsService {
            return GoalsServiceFactory.makeGoalsService(
                //BuildConfig.DEBUG,
                isDebug = false,
                isUnitTesting = false
            )
        }
    }

    @Binds
    abstract fun bindGoalsRemote(projectsRemote: GoalsRemoteImpl): GoalsRemote

}