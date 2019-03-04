package com.ovrbach.qapitalchallengerebooted.test

import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
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


}