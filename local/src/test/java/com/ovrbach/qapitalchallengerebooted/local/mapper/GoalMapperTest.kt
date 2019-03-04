package com.ovrbach.qapitalchallengerebooted.local.mapper

import com.ovrbach.qapitalchallengerebooted.local.GoalDataFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class GoalMapperTest {

    private val mapper = GoalMapper()
    @Test
    fun mapFromEntity() {
        val entity = GoalDataFactory.makeGoalEntity()
        val goal = mapper.mapFromEntity(entity)

        assertEquals(entity.goalImageURL, goal.goalImageURL)
        assertEquals(entity.userId, goal.userId)
        assertEquals(entity.targetAmount, goal.targetAmount)
        assertEquals(entity.currentBalance, goal.currentBalance)
        assertEquals(entity.status, goal.status)
        assertEquals(entity.name, goal.name)
        assertEquals(entity.id, goal.id)
    }

    @Test
    fun mapToEntity() {
        val goal = GoalDataFactory.makeGoal()
        val entity = mapper.mapToEntity(goal)

        assertEquals(goal.goalImageURL, entity.goalImageURL)
        assertEquals(goal.userId, entity.userId)
        assertEquals(goal.targetAmount, entity.targetAmount)
        assertEquals(goal.currentBalance, entity.currentBalance)
        assertEquals(goal.status, entity.status)
        assertEquals(goal.name, entity.name)
        assertEquals(goal.id, entity.id)
    }
}