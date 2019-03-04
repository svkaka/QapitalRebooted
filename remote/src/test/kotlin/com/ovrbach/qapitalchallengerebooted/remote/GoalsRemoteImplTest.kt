package com.ovrbach.qapitalchallengerebooted.remote

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.remote.entity.GoalEntity
import com.ovrbach.qapitalchallengerebooted.remote.entity.GoalsResponse
import com.ovrbach.qapitalchallengerebooted.remote.mapper.GoalMapper
import com.ovrbach.qapitalchallengerebooted.remote.service.GoalsService
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GoalsRemoteImplTest {

    private val mapper = mock<GoalMapper>()
    private val service = mock<GoalsService>()
    private val remote = GoalsRemoteImpl(service, mapper)

    @Test
    fun getGoalsCompletes() {
        stubGetGoals(Observable.just(GoalDataFactory.makeGoalsResponse()))
        stubEntityMapper(any(), GoalDataFactory.makeGoal())

        val testObserver = remote.getSavedGoals().test()
        testObserver.assertComplete()
    }

    @Test
    fun getGoalsCompleteCallsRemote() {
        stubGetGoals(Observable.just(GoalDataFactory.makeGoalsResponse()))
        stubEntityMapper(any(), GoalDataFactory.makeGoal())

        remote.getSavedGoals().test()
        verify(service).getSavedGoals()
    }

    @Test
    fun getGoalsReturnsData() {
        val data = GoalDataFactory.makeGoalsResponse()
        stubGetGoals(Observable.just(data))
        val models = mutableListOf<Goal>()
        data.savingsGoals.forEach {
            val model = GoalDataFactory.makeGoal()
            models.add(model)
            stubEntityMapper(it, model)
        }

        val testObserver = remote.getSavedGoals().test()
        testObserver.assertValue(models)
    }

    private fun stubGetGoals(observable: Observable<GoalsResponse>) {
        whenever(service.getSavedGoals())
            .thenReturn(observable)
    }

    private fun stubEntityMapper(entity: GoalEntity, domainEntity: Goal) {
        whenever(mapper.mapFromEntity(entity))
            .thenReturn(domainEntity)
    }
}