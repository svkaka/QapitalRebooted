package com.ovrbach.qapitalchallengerebooted.inject.module

import com.ovrbach.qapitalchallengerebooted.domain.repository.SavedGoalsRepository
import dagger.Module
import dagger.Provides
import org.mockito.Mockito

@Module
object TestDataModule {

    @Provides
    @JvmStatic
    fun provideGoalsLocal(): SavedGoalsRepository {
        return Mockito.mock(SavedGoalsRepository::class.java)
    }}