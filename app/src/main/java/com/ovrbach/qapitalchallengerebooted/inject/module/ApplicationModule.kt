package com.ovrbach.qapitalchallengerebooted.inject.module

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule{

    @Binds
    abstract fun bindContext(app: Application): Context
}