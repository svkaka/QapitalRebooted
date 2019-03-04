package com.ovrbach.qapitalchallengerebooted.domain.interactor.goals

import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.executor.PostExecutionThread
import com.ovrbach.qapitalchallengerebooted.domain.interactor.ObservableUseCase
import com.ovrbach.qapitalchallengerebooted.domain.repository.SavedGoalsRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetGoals @Inject constructor(
    private val savedGoalsRepository: SavedGoalsRepository,
    postExecutionThread: PostExecutionThread
) : ObservableUseCase<List<Goal>, Nothing>(postExecutionThread) {

    override fun buildUseCase(params: Nothing?): Observable<List<Goal>> {
        return savedGoalsRepository.getSavedGoals()
    }
}