package com.ovrbach.qapitalchallengerebooted.domain.entity

import java.io.Serializable
import java.util.*

data class Goal(
    var goalImageURL: String,
    var userId: UserId,
    var targetAmount: Float? = null,
    var currentBalance: Float = 0f,
    var status: String,
    var name: String,
    var id: GoalId,
    var connectedUsers: List<Int>? = null,
    var created: Array<String> = emptyArray()
) : Serializable {


    override fun toString(): String {
        return "Goal(goalImageURL='$goalImageURL', userId=$userId, targetAmount=$targetAmount, currentBalance=$currentBalance, created=${Arrays.toString(
            created
        )}, status='$status', name='$name', id=$id, connectedUsers=$connectedUsers)"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Goal

        if (goalImageURL != other.goalImageURL) return false
        if (userId != other.userId) return false
        if (targetAmount != other.targetAmount) return false
        if (currentBalance != other.currentBalance) return false
        if (status != other.status) return false
        if (name != other.name) return false
        if (id != other.id) return false
        if (connectedUsers != other.connectedUsers) return false

        return true
    }

    override fun hashCode(): Int {
        var result = goalImageURL.hashCode()
        result = 31 * result + userId
        result = 31 * result + (targetAmount?.hashCode() ?: 0)
        result = 31 * result + currentBalance.hashCode()
        result = 31 * result + status.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + id
        return result
    }
}