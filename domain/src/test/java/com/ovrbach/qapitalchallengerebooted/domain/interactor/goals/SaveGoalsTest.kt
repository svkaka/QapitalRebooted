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

class SaveGoalsTest {


    private lateinit var saveGoals: SaveGoals

    @Mock
    private lateinit var savedGoalsRepository: SavedGoalsRepository
    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        saveGoals = SaveGoals(savedGoalsRepository, postExecutionThread)
    }

    @Test
    fun saveGoalsCompletes() {
        stubGetGoal(Completable.complete())
        val testObserver = saveGoals.buildUserCase(
            SaveGoals.Params.forGoals(GoalDataFactory.makeGoals(3))
        ).test()
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun saveGoalsThrowException() {
        saveGoals.buildUserCase().test()
    }

    private fun stubGetGoal(completable: Completable) {
        whenever(savedGoalsRepository.saveGoals(any()))
            .thenReturn(completable)
    }
}