package com.ovrbach.qapitalchallengerebooted.domain.interactor.goals

import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.entity.GoalId
import com.ovrbach.qapitalchallengerebooted.domain.executor.PostExecutionThread
import com.ovrbach.qapitalchallengerebooted.domain.interactor.CompletableUseCase
import com.ovrbach.qapitalchallengerebooted.domain.repository.SavedGoalsRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteGoal @Inject constructor(
    private val savedGoalsRepository: SavedGoalsRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<DeleteGoal.Params>(postExecutionThread) {

    override fun buildUserCase(params: DeleteGoal.Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return savedGoalsRepository.deleteGoal(params.id)
    }

    data class Params constructor(val id: GoalId) {
        companion object {
            fun forGoal(goal: Goal) = Params(goal.id)
            fun forGoalId(goalId: GoalId) = Params(goalId)
        }
    }
}