package com.ovrbach.qapitalchallengerebooted.data.repository

import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.entity.GoalId
import io.reactivex.Completable
import io.reactivex.Observable

interface GoalsLocal {

    fun getSavedGoals(): Observable<List<Goal>>

    fun clearSavedGoals(): Completable

    fun saveGoals(list: List<Goal>): Completable

    fun updateDatabase(old: List<Goal>, new: List<Goal>): List<Goal>

    fun deleteSavedGoal(goalId: GoalId): Completable
}