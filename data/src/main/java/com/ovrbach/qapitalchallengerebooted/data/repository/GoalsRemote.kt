package com.ovrbach.qapitalchallengerebooted.data.repository

import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import io.reactivex.Completable
import io.reactivex.Observable

interface GoalsRemote {

    fun getSavedGoals(): Observable<List<Goal>>

}