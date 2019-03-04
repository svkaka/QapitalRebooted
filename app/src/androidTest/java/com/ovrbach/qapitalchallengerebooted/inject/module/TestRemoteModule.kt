package com.ovrbach.qapitalchallengerebooted.inject.module

import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsRemote
import com.ovrbach.qapitalchallengerebooted.remote.service.GoalsService
import com.ovrbach.qapitalchallengerebooted.remote.service.GoalsServiceFactory
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
object TestRemoteModule {

    @Provides
    @JvmStatic
    fun provideGoalsService(): GoalsService {
         return Mockito.mock(GoalsService::class.java)
//        return GoalsServiceFactory.makeGoalsService(
//            isDebug = true,
//            isUnitTesting = true
//        )
    }

    @Provides
    @JvmStatic
    fun provideGoalsRemote(): GoalsRemote {
        return Mockito.mock(GoalsRemote::class.java)
    }

}