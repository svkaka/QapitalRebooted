package com.ovrbach.qapitalchallengerebooted.remote.mapper

import com.ovrbach.qapitalchallengerebooted.domain.entity.Goal
import com.ovrbach.qapitalchallengerebooted.domain.mapper.EntityMapper
import com.ovrbach.qapitalchallengerebooted.remote.entity.GoalEntity
import javax.inject.Inject

open class GoalMapper @Inject constructor() : EntityMapper<GoalEntity, Goal> {

    override fun mapFromEntity(entity: GoalEntity): Goal {
        return Goal(
            goalImageURL = entity.goalImageURL,
            userId = entity.userId,
            targetAmount = entity.targetAmount,
            currentBalance = entity.currentBalance,
            status = entity.status,
            name = entity.name,
            id = entity.id,
            connectedUsers = entity.connectedUsers,
            created = entity.created
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
            id = dataEntity.id,
            connectedUsers = dataEntity.connectedUsers,
            created = dataEntity.created
        )
    }
}