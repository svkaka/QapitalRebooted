package com.ovrbach.qapitalchallengerebooted.feature

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.ovrbach.qapitalchallengerebooted.R
import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.repository.SavedGoalsRepository
import com.ovrbach.qapitalchallengerebooted.feature.goals.BrowseGoalsAdapter
import com.ovrbach.qapitalchallengerebooted.feature.goals.GoalsActivity
import com.ovrbach.qapitalchallengerebooted.test.GoalDataFactory
import com.ovrbach.qapitalchallengerebooted.test.TestApp
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class GoalsActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule(GoalsActivity::class.java, false, false)

    private val savedGoalsRepository: SavedGoalsRepository = TestApp.appComponent().goalsRepository()

    @Test
    fun activityLaunches() {
        stubRepositoryGetGoal(
            Observable.just(GoalDataFactory.makeGoals(2))
        )
        activity.launchActivity(null)
    }

    @Test
    fun goalsAreLoaded() {
        val data = GoalDataFactory.makeGoals(2)
        stubRepositoryGetGoal(
            Observable.just(data)
        )
        activity.launchActivity(null)

        val recycler = onView(withId(R.id.goals_recycler))
        data.forEachIndexed { index, goal ->
            recycler.perform(RecyclerViewActions.scrollToPosition<BrowseGoalsAdapter.Holder>(index))
            recycler.check(matches(hasDescendant(withText(goal.name))))
        }
    }

    private fun stubRepositoryGetGoal(observable: Observable<List<Goal>>) {
        Mockito.`when`(
            savedGoalsRepository.getSavedGoals()
        ).thenReturn(observable)
    }

}