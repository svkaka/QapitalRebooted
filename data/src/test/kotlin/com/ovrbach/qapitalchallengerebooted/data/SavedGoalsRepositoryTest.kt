package com.ovrbach.qapitalchallengerebooted.data

import com.nhaarman.mockitokotlin2.*
import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsLocal
import com.ovrbach.qapitalchallengerebooted.data.repository.GoalsRemote
import com.ovrbach.qapitalchallengerebooted.data.store.GoalStoreFactory
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SavedGoalsRepositoryTest {

    val local = mock<GoalsLocal>()
    val remote = mock<GoalsRemote>()
    val factory = mock<GoalStoreFactory>()
    val repository: SavedGoalsRepositoryImpl = SavedGoalsRepositoryImpl(factory)

    @Before
    fun setUp() {
        stubFactory()
    }

    private fun stubFactory() {
        stubLocal()
        stubRemote()
    }

    private fun stubLocal() {
        whenever(factory.getLocalStore())
            .thenReturn(local)
    }

    private fun stubRemote() {
        whenever(factory.getRemoteStore())
            .thenReturn(remote)
    }

    @Test
    fun getSavedGoalsCompletes() {
        stubGetSavedGoals(Observable.just(GoalDataFactory.makeGoals(2)))
        val testObserver = repository.getSavedGoals().test()
        testObserver.assertComplete()
    }

    @Test
    fun getSavedGoalsReturnsData() {
        val data = GoalDataFactory.makeGoals(2)
        stubGetSavedGoals(Observable.just(data))
        val testObserver = repository.getSavedGoals().test()

        testObserver.assertValueAt(0, data)
        testObserver.assertValueAt(1, data)
    }


    @Test
    fun updateDatabase() {
        val data = GoalDataFactory.makeGoals(2)
        stubGetSavedGoals(Observable.just(data))
        repository.getSavedGoals().test()

        verify(factory.getLocalStore(), times(1)).updateDatabase(data, data)
    }

    private fun stubGetSavedGoals(observable: Observable<List<Goal>>) {
        whenever(factory.getRemoteStore().getSavedGoals())
            .thenReturn(observable)
        whenever(factory.getLocalStore().getSavedGoals())
            .thenReturn(observable)
    }

    @Test
    fun getSavedGoalsLocalErrorRemoteNext() {
        val data = GoalDataFactory.makeGoals(2)
        val error = Exception()

        val local = Observable.error<List<Goal>>(error)
        val remote = Observable.just(data)
        stubGetSavedGoalsError1(local, remote)
        val testObserver = repository.getSavedGoals().test()

        testObserver.assertComplete()

        testObserver.assertValueAt(0, emptyList())
        testObserver.assertValueAt(1, data)
    }

    private fun stubGetSavedGoalsError1(local: Observable<List<Goal>>, remote: Observable<List<Goal>>) {
        whenever(factory.getRemoteStore().getSavedGoals())
            .thenReturn(remote)
        whenever(factory.getLocalStore().getSavedGoals())
            .thenReturn(local)
    }

    @Test
    fun getSavedGoalsLocalNextRemoteError() {
        val data = GoalDataFactory.makeGoals(2)
        val error = Exception()

        val local = Observable.just(data)
        val remote = Observable.error<List<Goal>>(error)
        stubGetSavedGoalsError2(local, remote)
        val testObserver = repository.getSavedGoals().test()

        testObserver.assertComplete()

        testObserver.assertValueAt(0, data)
        testObserver.assertValueAt(1, emptyList())
    }

    private fun stubGetSavedGoalsError2(local: Observable<List<Goal>>, remote: Observable<List<Goal>>) {
        whenever(factory.getRemoteStore().getSavedGoals())
            .thenReturn(remote)
        whenever(factory.getLocalStore().getSavedGoals())
            .thenReturn(local)
    }

    @Test
    fun saveGoalsCompletes() {
        val items = GoalDataFactory.makeGoals(2)
        stubSaveGoals(Completable.complete())
        val testObserver = repository.saveGoals(items).test()
        testObserver.assertComplete()
    }

    private fun stubSaveGoals(completable: Completable) {
        whenever(factory.getLocalStore().saveGoals(any()))
            .thenReturn(completable)
    }

    @Test
    fun deleteGoalCompletes() {
        stubDeleteGoal(Completable.complete())
        val testObserver = repository.deleteGoal(GoalDataFactory.randomInt()).test()
        testObserver.assertComplete()
    }

    private fun stubDeleteGoal(completable: Completable) {
        whenever(factory.getLocalStore().deleteSavedGoal(any()))
            .thenReturn(completable)
    }
}