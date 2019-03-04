package com.ovrbach.qapitalchallengerebooted.domain.interactor.goals

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.ovrbach.qapitalchallengerebooted.domain.GoalDataFactory
import com.ovrbach.qapitalchallengerebooted.domain.executor.PostExecutionThread
import com.ovrbach.qapitalchallengerebooted.domain.repository.SavedGoalsRepository
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DeleteGoalTest {


    private lateinit var deleteGoal: DeleteGoal

    @Mock
    private lateinit var savedGoalsRepository: SavedGoalsRepository
    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        deleteGoal = DeleteGoal(savedGoalsRepository, postExecutionThread)
    }

    @Test
    fun deleteGoalByIdCompletes() {
        stubGetGoal(Completable.complete())
        val testObserver = deleteGoal.buildUserCase(
            DeleteGoal.Params.forGoalId(GoalDataFactory.randomInt())
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun deleteGoalCompletes() {
        stubGetGoal(Completable.complete())
        val testObserver = deleteGoal.buildUserCase(
            DeleteGoal.Params.forGoal(GoalDataFactory.makeGoal())
        )
        testObserver.test().assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun saveGoalsThrowException() {
        deleteGoal.buildUserCase().test()
    }

    private fun stubGetGoal(completable: Completable) {
        whenever(savedGoalsRepository.deleteGoal(any()))
            .thenReturn(completable)
    }
}