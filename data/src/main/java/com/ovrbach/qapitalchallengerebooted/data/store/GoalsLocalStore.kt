package com.ovrbach.qapitalchallengerebooted.data.store

import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsDataStore
import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsLocal
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.entity.GoalId
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

open class GoalsLocalStore @Inject constructor(
    private val goals: GoalsLocal
) : GoalsDataStore {

    override fun getSavedGoals(): Observable<List<Goal>> {
        return goals.getSavedGoals()
    }

    override fun deleteSavedGoal(goalId: GoalId): Completable {
        return goals.deleteSavedGoal(goalId)
    }

    override fun clearSavedGoals(): Completable {
        return goals.clearSavedGoals()
    }

    override fun saveGoals(list: List<Goal>): Completable {
        return goals.saveGoals(list)
    }

    //todo rethink
    override fun updateDatabase(local: List<Goal>, upToDate: List<Goal>): List<Goal> {
        return goals.updateDatabase(local, upToDate)
    }

}