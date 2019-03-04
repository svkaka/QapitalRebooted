package com.ovrbach.qapitalchallengerebooted.data.store

import com.nhaarman.mockitokotlin2.*
import com.ovrbach.qapitalchallengerebooted.data.GoalDataFactory
import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsLocal
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GoalsLocalStoreTest {

    val local = mock<GoalsLocal>()
    val store = GoalsLocalStore(local)

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
        verify(local).getSavedGoals()
    }

    private fun stubLocalProjects(observable: Observable<List<Goal>>) {
        whenever(local.getSavedGoals())
            .thenReturn(observable)
    }


    @Test
    fun clearGoalsCompletes() {
        stubClearGoalsCompletes(Completable.complete())
        val testObserver = store.clearSavedGoals().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearGoalsCallsLocal() {
        stubClearGoalsCompletes(Completable.complete())
        store.clearSavedGoals().test()
        verify(local).clearSavedGoals()
    }

    private fun stubClearGoalsCompletes(completable: Completable) {
        whenever(store.clearSavedGoals())
            .thenReturn(completable)
    }


    @Test
    fun saveGoalsCompletes() {
        val data = GoalDataFactory.makeGoals(2)
        stubSaveGoals(data,Completable.complete())
        val testObserver = store.saveGoals(data).test()
         testObserver.assertComplete()
    }

    @Test
    fun saveGoalsCallsLocal() {
        val data = GoalDataFactory.makeGoals(2)
        stubSaveGoals(data, Completable.complete())
        store.saveGoals(data).test()
        verify(local).saveGoals(data)
    }

    private fun stubSaveGoals(data: List<Goal>, completable: Completable) {
        whenever(store.saveGoals(data))
            .thenReturn(completable)
    }


    @Test
    fun deleteGoalCompletes() {
        stubDeleteGoalsCompletes(Completable.complete())
        val data = GoalDataFactory.randomInt()
        val testObserver = store.deleteSavedGoal(data).test()
        testObserver.assertComplete()
    }

    @Test
    fun deleteGoalCallsLocal() {
        stubDeleteGoalsCompletes(Completable.complete())
        val data = GoalDataFactory.randomInt()
        store.deleteSavedGoal(data).test()
        verify(local).deleteSavedGoal(data)
    }

    private fun stubDeleteGoalsCompletes(completable: Completable) {
        whenever(store.deleteSavedGoal(any()))
            .thenReturn(completable)
    }

}