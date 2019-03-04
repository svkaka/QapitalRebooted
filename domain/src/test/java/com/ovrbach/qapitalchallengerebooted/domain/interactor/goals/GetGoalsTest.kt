package com.ovrbach.qapitalchallengerebooted.domain.interactor.goals

import com.nhaarman.mockitokotlin2.whenever
import com.ovrbach.qapitalchallengerebooted.domain.GoalDataFactory
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.executor.PostExecutionThread
import com.ovrbach.qapitalchallengerebooted.domain.repository.SavedGoalsRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetGoalsTest {

    private lateinit var getGoals: GetGoals

    @Mock
    private lateinit var savedGoalsRepository: SavedGoalsRepository
    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getGoals = GetGoals(savedGoalsRepository, postExecutionThread)
    }

    @Test
    fun getGoalsCompletes() {
        stubGetGoal(Observable.just(GoalDataFactory.makeGoals(5)))
        val testObserver = getGoals.buildUseCase().test()
        testObserver.assertComplete()
    }

    @Test
    fun getGoalsReturnData() {
        val data = GoalDataFactory.makeGoals(5)
        stubGetGoal(Observable.just(data))
        val testObserver = getGoals.buildUseCase().test()
        testObserver.assertValue(data)
    }

    private fun stubGetGoal(observable: Observable<List<Goal>>) {
        whenever(savedGoalsRepository.getSavedGoals())
            .thenReturn(observable)
    }
}