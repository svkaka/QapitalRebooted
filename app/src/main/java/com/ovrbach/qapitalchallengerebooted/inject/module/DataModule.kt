package com.ovrbach.qapitalchallengerebooted.inject.module

import com.ovrbach.qapitalchallengerebooted.data.SavedGoalsRepositoryImpl
import com.ovrbach.qapitalchallengerebooted.domain.repository.SavedGoalsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepository(dataRepository: SavedGoalsRepositoryImpl): SavedGoalsRepository
}