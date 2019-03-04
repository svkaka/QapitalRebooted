package com.ovrbach.qapitalchallengerebooted.remote.mapper

import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.remote.GoalDataFactory
import com.ovrbach.qapitalchallengerebooted.remote.entity.GoalEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class GoalMapperTest {


    private val mapper = GoalMapper()

    @Test
    fun mapFromEntity() {
        val goalEntity: GoalEntity = GoalDataFactory.makeGoalEntity()
        val goal: Goal = mapper.mapFromEntity(goalEntity)

        assertEquals(goalEntity.goalImageURL, goal.goalImageURL)
        assertEquals(goalEntity.userId, goal.userId)
        assertEquals(goalEntity.targetAmount, goal.targetAmount)
        assertEquals(goalEntity.currentBalance, goal.currentBalance)
        assertEquals(goalEntity.status, goal.status)
        assertEquals(goalEntity.name, goal.name)
        assertEquals(goalEntity.id, goal.id)
        assertEquals(goalEntity.connectedUsers, goal.connectedUsers)
        assertEquals(goalEntity.created, goal.created)
    }

    @Test
    fun mapToEntity() {
        val goal: Goal = GoalDataFactory.makeGoal()
        val goalEntity: GoalEntity = mapper.mapToEntity(goal)

        assertEquals(goal.goalImageURL, goalEntity.goalImageURL)
        assertEquals(goal.userId, goalEntity.userId)
        assertEquals(goal.targetAmount, goalEntity.targetAmount)
        assertEquals(goal.currentBalance, goalEntity.currentBalance)
        assertEquals(goal.status, goalEntity.status)
        assertEquals(goal.name, goalEntity.name)
        assertEquals(goal.id, goalEntity.id)
        assertEquals(goal.connectedUsers, goalEntity.connectedUsers)
        assertEquals(goal.created, goalEntity.created)
    }
}