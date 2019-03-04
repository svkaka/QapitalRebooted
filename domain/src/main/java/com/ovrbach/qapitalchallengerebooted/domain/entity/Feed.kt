package com.ovrbach.qapitalchallengerebooted.domain.entity

import com.ovrbach.qapitalchallengerebooted.domain.logic.parseTime
import java.util.*

data class Feed(
    var id: FeedId,
    var type: String,
    var timestamp: String,
    var message: String,
    var amount: Float,
    var userId: Int,
    var goalId: GoalId = -1,
    val date: Date = timestamp.parseTime()
) {

}

fun List<Feed>.updateGoalId(id: GoalId) {
    this.forEach { it.goalId = id }
}