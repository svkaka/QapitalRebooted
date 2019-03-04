package com.ovrbach.qapitalchallengerebooted.domain.interactor.goals

import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.executor.PostExecutionThread
import com.ovrbach.qapitalchallengerebooted.domain.interactor.CompletableUseCase
import com.ovrbach.qapitalchallengerebooted.domain.repository.SavedGoalsRepository
import io.reactivex.Completable
import javax.inject.Inject

class SaveGoals @Inject constructor(
    private val savedGoalsRepository: SavedGoalsRepository,
    postExecutionThread: PostExecutionThread
) : CompletableUseCase<SaveGoals.Params>(postExecutionThread) {

    override fun buildUserCase(params: SaveGoals.Params?): Completable {
        if (params == null) throw IllegalArgumentException("Params can't be null")
        return savedGoalsRepository.saveGoals(params.list)
    }

    data class Params constructor(val list: List<Goal>) {
        companion object {
            fun forGoals(goals: List<Goal>) = Params(goals)
        }
    }
}