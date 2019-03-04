package com.ovrbach.qapitalchallengerebooted.data.store

import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsDataStore
import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsRemote
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.entity.GoalId
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

open class GoalsRemoteStore @Inject constructor(
    private val goals: GoalsRemote
) : GoalsDataStore {

    override fun getSavedGoals(): Observable<List<Goal>> {
        return goals.getSavedGoals()
    }

    override fun updateDatabase(local: List<Goal>, upToDate: List<Goal>): List<Goal> {
        throw UnsupportedOperationException("GoalsRemoteStore doesn't support this method")
    }

    override fun deleteSavedGoal(goalId: GoalId): Completable {
        throw UnsupportedOperationException("GoalsRemoteStore doesn't support this method")
    }

    override fun clearSavedGoals(): Completable {
        throw UnsupportedOperationException("GoalsRemoteStore doesn't support this method")
    }

    override fun saveGoals(list: List<Goal>): Completable {
        throw UnsupportedOperationException("GoalsRemoteStore doesn't support this method")
    }
}