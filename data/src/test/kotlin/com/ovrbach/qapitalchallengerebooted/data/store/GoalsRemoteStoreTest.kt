package com.ovrbach.qapitalchallengerebooted.data.store

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.ovrbach.qapitalchallengerebooted.data.GoalDataFactory
import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsRemote
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import io.reactivex.Observable
import org.junit.Test

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.lang.UnsupportedOperationException

@RunWith(JUnit4::class)
class GoalsRemoteStoreTest {

    val remote = mock<GoalsRemote>()
    val store = GoalsRemoteStore(remote)

    @Test
    fun getGoalsCompletes() {
        stubLocalProjects(Observable.just(GoalDataFactory.makeGoals(2)))
        val testObserver = store.getSavedGoals().test()
        testObserver.assertComplete()
    }

    @Test
    fun getGoalsReturnsData() {
        val data = GoalDataFactory.makeGoals(2)
        stubLocalProjects(Observable.just(data))
        val testObserver = store.getSavedGoals().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getGoalsCallsLocal() {
        stubLocalProjects(Observable.just(GoalDataFactory.makeGoals(2)))
        store.getSavedGoals().test()
        verify(remote).getSavedGoals()
    }

    private fun stubLocalProjects(observable: Observable<List<Goal>>) {
        whenever(remote.getSavedGoals())
            .thenReturn(observable)
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getNotUpToDateGoals() {
        store.updateDatabase(emptyList(), emptyList())
    }

    @Test(expected = UnsupportedOperationException::class)
    fun deleteSavedGoal() {
        store.deleteSavedGoal(GoalDataFactory.randomInt()).test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun clearSavedGoals() {
        store.clearSavedGoals().test()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun saveGoals() {
        store.saveGoals(emptyList())
    }
}