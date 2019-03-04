package com.ovrbach.qapitalchallengerebooted.data

import com.ovrbach.qapitalchallengerebooted.data.store.GoalStoreFactory
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.entity.GoalId
import com.ovrbach.qapitalchallengerebooted.domain.repository.SavedGoalsRepository
import com.ovrbach.qapitalchallengerebooted.domain.util.safeLet
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class SavedGoalsRepositoryImpl @Inject constructor(
    private val factory: GoalStoreFactory
) : SavedGoalsRepository {

    override fun getSavedGoals(): Observable<List<Goal>> {
        var localData: List<Goal>? = null
        var remoteData: List<Goal>? = null

        val localObservable = factory.getLocalStore().getSavedGoals()
            .doOnNext {
                localData = it
                safeLet(localData, remoteData) { local, remote ->
                    factory.getLocalStore().updateDatabase(old = local, new = remote)
                }
            }

        val remoteObservable = factory.getRemoteStore().getSavedGoals()
            .doOnNext {
                remoteData = it
                safeLet(localData, remoteData) { local, remote ->
                    factory.getLocalStore().updateDatabase(old = local, new = remote)
                }
            }.onErrorReturn { emptyList() }
        //todo we could send Response<Error>(e, meta {src, params}

        return Observable.merge(localObservable, remoteObservable)
    }

    override fun saveGoals(list: List<Goal>): Completable {
        return factory.getLocalStore().saveGoals(list)
    }

    override fun deleteGoal(id: GoalId): Completable {
        return factory.getLocalStore().deleteSavedGoal(id)
    }
}