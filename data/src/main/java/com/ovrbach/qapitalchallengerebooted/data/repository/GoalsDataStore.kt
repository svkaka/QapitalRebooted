package com.ovrbach.qapitalchallengerebooted.data.repository

import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.entity.GoalId
import io.reactivex.Completable
import io.reactivex.Observable

interface GoalsDataStore{

    fun getSavedGoals(): Observable<List<Goal>>

    fun clearSavedGoals(): Completable

    fun saveGoals(list: List<Goal>): Completable

    fun deleteSavedGoal(goalId: GoalId): Completable

    fun updateDatabase(local: List<Goal>, upToDate: List<Goal>): List<Goal>
}