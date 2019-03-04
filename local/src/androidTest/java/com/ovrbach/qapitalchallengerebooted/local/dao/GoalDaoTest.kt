package com.ovrbach.qapitalchallengerebooted.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ovrbach.qapitalchallengerebooted.local.AppDatabase
import com.ovrbach.qapitalchallengerebooted.local.GoalDataFactoryAndroid
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class GoalDaoTest {

    @Rule
    @JvmField var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        AppDatabase::class.java
    )
        .allowMainThreadQueries()
        .build()

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun getSavedGoalsReturnsData() {
        val data = GoalDataFactoryAndroid.makeGoalEntities(1)
        database.goals().insert(data)
        val testObserver = database.goals().getSavedGoals()
    }

    @Test
    fun deleteGoal() {
        val data = GoalDataFactoryAndroid.makeGoalEntities(2)
        database.goals().insert(data).test()
        val toBeDeleted = data[0]
        data.remove(toBeDeleted)
        database.goals().delete(toBeDeleted).test()

        val testObserver = database.goals().getSavedGoals().test()
        testObserver.assertValue(data)
    }
}