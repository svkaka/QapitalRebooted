package com.ovrbach.qapitalchallengerebooted.remote.entity

import com.ovrbach.qapitalchallengerebooted.domain.entity.GoalId
import com.ovrbach.qapitalchallengerebooted.domain.entity.UserId


class GoalEntity(
    var goalImageURL: String,
    var userId: UserId,
    var targetAmount: Float? = null,
    var currentBalance: Float = 0f,
    var status: String,
    var name: String,
    var id: GoalId,
    var connectedUsers: List<Int>? = null,
    var created: Array<String> = emptyArray()
)