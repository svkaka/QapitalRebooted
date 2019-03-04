package com.ovrbach.qapitalchallengerebooted.presentation

import android.accounts.NetworkErrorException
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.eq
import com.ovrbach.qapitalchallengerebooted.domain.Response
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.interactor.goals.GetGoals
import io.reactivex.observers.DisposableObserver
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@RunWith(JUnit4::class)
class GoalsViewModelTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    val getGoals = mock(GetGoals::class.java)
    val goalsViewModel = GoalsViewModel(getGoals)

    @Captor

    val captor = argumentCaptor<DisposableObserver<List<Goal>>>()

    @Test
    fun onCleared() {

    }

    @Test
    fun getProjects() {
        goalsViewModel.fetchProjects()
        verify(getGoals).execute(any(), eq(null))
    }

    @Test
    fun fetchProjectsSuccess() {
        val goals = GoalDataFactory.makeGoals(2)
        goalsViewModel.fetchProjects()

        verify(getGoals).execute(
            captor.capture(),
            eq(null)
        )
        captor.firstValue.onNext(goals)
        assertTrue { goalsViewModel.getProjects().value is Response.Success<List<Goal>> }
        val response = goalsViewModel.getProjects().value as Response.Success<List<Goal>>
        assertEquals(2, response.data.size)
        assertEquals(goals[0], response.data[0])
    }

    @Test
    fun fetchProjectsError() {
        goalsViewModel.fetchProjects()

        verify(getGoals).execute(
            captor.capture(),
            eq(null)
        )
        captor.firstValue.onError(NetworkErrorException("No network error"))
        assertTrue { goalsViewModel.getProjects().value is Response.Error }
        val response = goalsViewModel.getProjects().value as Response.Error
        assertTrue { response.error != null }
    }

    @Test
    fun fetchProjectsWaiting() {
        goalsViewModel.fetchProjects()
        assertTrue { goalsViewModel.getProjects().value is Response.Waiting }
    }
}