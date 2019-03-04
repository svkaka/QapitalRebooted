package com.ovrbach.qapitalchallengerebooted.remote

import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.remote.entity.GoalEntity
import com.ovrbach.qapitalchallengerebooted.remote.entity.GoalsResponse
import java.util.*

internal object GoalDataFactory {

    fun randomInt(): Int = Random().nextInt(100)
    fun randomFloat(): Float = Random().nextFloat()
    fun randomString() = UUID.randomUUID().toString()
    fun randomBoolean() = Math.random() < 0.5
    fun randomCreatedAt() = arrayOf(randomString(), randomString(), randomString())

    fun makeGoal(): Goal = Goal(
        goalImageURL = randomString(),
        userId = randomInt(),
        targetAmount = randomFloat(),
        currentBalance = randomFloat(),
        status = randomString(),
        name = randomString(),
        id = randomInt(),
        connectedUsers = null,
        created = randomCreatedAt()
    )

    fun makeGoals(count: Int): List<Goal> {
        val goals = mutableListOf<Goal>()
        repeat(count) {
            goals.add(makeGoal())
        }
        return goals
    }

    fun makeGoalEntity() = GoalEntity(
        goalImageURL = randomString(),
        userId = randomInt(),
        targetAmount = randomFloat(),
        currentBalance = randomFloat(),
        status = randomString(),
        name = randomString(),
        id = randomInt(),
        connectedUsers = null,
        created = randomCreatedAt()
    )

    fun makeGoalEntities(count: Int): MutableList<GoalEntity> {
        val goals = mutableListOf<GoalEntity>()
        repeat(count) {
            goals.add(makeGoalEntity())
        }
        return goals
    }

    fun makeGoalsResponse(): GoalsResponse = GoalsResponse(
        makeGoalEntities(2)
    )

}