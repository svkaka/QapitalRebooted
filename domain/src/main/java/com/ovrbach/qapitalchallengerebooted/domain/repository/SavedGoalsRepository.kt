package com.ovrbach.qapitalchallengerebooted.domain.repository

import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.entity.GoalId
import io.reactivex.Completable
import io.reactivex.Observable

interface SavedGoalsRepository {

    fun getSavedGoals(): Observable<List<Goal>>

    fun saveGoals(list: List<Goal>): Completable

    fun deleteGoal(id: GoalId): Completable

}