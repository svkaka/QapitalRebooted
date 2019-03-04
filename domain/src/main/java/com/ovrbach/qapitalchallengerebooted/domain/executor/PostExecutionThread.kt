package com.ovrbach.qapitalchallengerebooted.domain.executor

import io.reactivex.Scheduler

interface PostExecutionThread{
    val scheduler: Scheduler
}