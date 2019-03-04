package com.ovrbach.qapitalchallengerebooted.local

import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.local.entity.GoalEntity
import java.util.*

object GoalDataFactoryAndroid {

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
        id = randomInt()
    )

    fun makeGoalEntities(count: Int): MutableList<GoalEntity> {
        val goals = mutableListOf<GoalEntity>()
        repeat(count) {
            goals.add(makeGoalEntity())
        }
        return goals
    }
}