package com.ovrbach.qapitalchallengerebooted.local.mapper


import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.mapper.EntityMapper
import com.ovrbach.qapitalchallengerebooted.local.entity.GoalEntity
import javax.inject.Inject

class GoalMapper @Inject constructor() : EntityMapper<GoalEntity, Goal> {

    override fun mapFromEntity(entity: GoalEntity): Goal {
        return Goal(
            goalImageURL = entity.goalImageURL,
            userId = entity.userId,
            targetAmount = entity.targetAmount,
            currentBalance = entity.currentBalance,
            status = entity.status,
            name = entity.name,
            id = entity.id,
            connectedUsers = null,
            created = emptyArray()
        )
    }

    override fun mapToEntity(dataEntity: Goal): GoalEntity {
        return GoalEntity(
            goalImageURL = dataEntity.goalImageURL,
            userId = dataEntity.userId,
            targetAmount = dataEntity.targetAmount,
            currentBalance = dataEntity.currentBalance,
            status = dataEntity.status,
            name = dataEntity.name,
            id = dataEntity.id
        )
    }

}