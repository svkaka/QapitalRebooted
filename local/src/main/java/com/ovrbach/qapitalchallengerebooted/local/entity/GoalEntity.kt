package com.ovrbach.qapitalchallengerebooted.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ovrbach.qapitalchallengerebooted.domain.entity.GoalId
import com.ovrbach.qapitalchallengerebooted.domain.entity.UserId

@Entity(tableName = "goal")
open class GoalEntity(
    var goalImageURL: String,
    var userId: UserId,
    var targetAmount: Float? = null,
    var currentBalance: Float = 0f,
    var status: String,
    var name: String,
    @PrimaryKey var id: GoalId
){
    override fun toString(): String {
        return "GoalEntity(targetAmount=$targetAmount, currentBalance=$currentBalance, name='$name')"
    }
}