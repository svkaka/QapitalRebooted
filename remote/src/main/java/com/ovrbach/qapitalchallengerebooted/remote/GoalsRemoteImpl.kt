package com.ovrbach.qapitalchallengerebooted.remote

import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsRemote
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.remote.mapper.GoalMapper
import com.ovrbach.qapitalchallengerebooted.remote.service.GoalsService
import io.reactivex.Observable
import javax.inject.Inject

class GoalsRemoteImpl @Inject constructor(
    private val service: GoalsService,
    private val mapper: GoalMapper
) : GoalsRemote {

    override fun getSavedGoals(): Observable<List<Goal>> {
        return service.getSavedGoals().map { response ->
            response.savingsGoals.map { mapper.mapFromEntity(it) }
        }
    }

}